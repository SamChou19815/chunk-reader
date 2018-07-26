package com.developersam.chunkreader

import com.developersam.auth.GoogleUser
import com.google.cloud.datastore.Entity
import com.google.cloud.datastore.Key
import com.google.cloud.language.v1beta2.Sentence
import com.google.cloud.language.v1beta2.TextSpan
import com.google.common.collect.Sets
import typedstore.TypedEntity
import typedstore.TypedEntityCompanion
import typedstore.TypedTable
import java.util.Arrays
import com.google.cloud.language.v1beta2.Entity as LanguageEntity

/**
 * [Summary] is used to fetch a list of summaries for a common text key for a given limit
 * (defaults to 5), which will be auto-corrected to 1 if it is below 1.
 */
class Summary private constructor() {

    /**
     * [Table] is the table definition for [Summary] sentences.
     */
    private object Table : TypedTable<Table>(tableName = "ChunkReaderSentence") {
        val beginOffset = longProperty(name = "begin_offset")
        val salience = doubleProperty(name = "salience")
        val text = longStringProperty(name = "text")
    }

    /**
     * [SentenceEntity] is the entity definition for [Summary] sentences.
     */
    private class SentenceEntity(entity: Entity) : TypedEntity<Table>(entity = entity) {
        val beginOffset: Long = Table.beginOffset.delegatedValue
        val text: String = Table.text.delegatedValue

        companion object : TypedEntityCompanion<Table, SentenceEntity>(table = Table) {
            override fun create(entity: Entity): SentenceEntity =
                    SentenceEntity(entity = entity)
        }
    }

    /**
     * [AnnotatedSentence] is essentially a sentence object marked with a [salience] value to
     * denote its importance.
     * It is used to build a summary generation system that lets the user specify number of
     * sentences to be included in the summary.
     */
    private class AnnotatedSentence(val beginOffset: Long, var salience: Double, val text: String) {

        /**
         * Randomly initialize itself from a [sentence] object from Google.
         */
        constructor(sentence: Sentence) : this(
                beginOffset = sentence.text.beginOffset.toLong(),
                salience = Math.random(),
                text = sentence.text.content
        )

    }

    /**
     * [SentenceSalienceMarker] is used to mark each sentence with a salience value by Text Rank
     * algorithm.
     * It will use the given processed data to help further analyze the importance of each sentence.
     *
     * @constructor constructed by the given `sentences` and `entities`.
     */
    private class SentenceSalienceMarker(
            sentences: List<Sentence>, entities: List<LanguageEntity>
    ) {

        /**
         * A list of [AnnotatedSentence] to be written into database.
         */
        val annotatedSentences: Array<AnnotatedSentence>
        /**
         * An array that represents the ranges of each sentence.
         */
        private val sentenceRanges: IntArray
        /**
         * An array of sets of keywords of each sentence.
         */
        private val keywordsArray: Array<MutableSet<String>>
        /**
         * The similarity matrix between sentences.
         */
        private val similarityMatrix: Array<DoubleArray>

        init {
            val num = sentences.size
            // Initialize instance variables
            annotatedSentences = Array(size = num) { i -> AnnotatedSentence(sentences[i]) }
            // Build an array of sentence ranges.
            sentenceRanges = IntArray(size = num) { i -> sentences[i].text.beginOffset }
            keywordsArray = Array(size = num) { HashSet<String>() }
            similarityMatrix = Array(size = num) { DoubleArray(num) }
            // Build the keywords array to find keywords mapped to each sentence.\
            entities.forEach { entity ->
                entity.mentionsList.forEach { entityMention ->
                    val entityText: TextSpan = entityMention.text
                    val sentenceID: Int = sentenceRanges.getBeginOffsetIndex(
                            beginOffset = entityText.beginOffset
                    )
                    keywordsArray[sentenceID].add(element = entityText.content)
                }
            }
            // Build the similarity matrix
            for (i in 0 until (num - 1)) {
                for (j in (i + 1) until num) {
                    val similarity = computeSimilarity(s1 = i, s2 = j)
                    similarityMatrix[i][j] = similarity
                    similarityMatrix[j][i] = similarity
                }
            }
        }

        /**
         * Obtain a random sentence id from the [annotatedSentences], which can have an optional
         * forbidden value.
         */
        private fun getRandomSentenceID(forbiddenValue: Int = -1): Int {
            while (true) {
                val randomValue = (Math.random() * annotatedSentences.size).toInt()
                if (randomValue != forbiddenValue) {
                    return randomValue
                }
            }
        }

        /**
         * A helper method to find the index of [beginOffset] from an array of begin offsets that
         * represents ranges of sentence.
         */
        private fun IntArray.getBeginOffsetIndex(beginOffset: Int): Int {
            val rawIndex = Arrays.binarySearch(this, beginOffset)
            if (rawIndex >= 0) {
                return rawIndex
            }
            // a + ~a + 1 = 0 ==> ~a = -1 - a => a = ~(-1 - a)
            val insertionPoint = rawIndex.inv()
            // Obtain the inversion point from API
            return insertionPoint - 1
        }

        /**
         * Calculate the similarity of two sentences, specified by their IDs ([s1], [s2])
         * as a [Double].
         */
        private fun computeSimilarity(s1: Int, s2: Int): Double {
            val numCommon = Sets.intersection(keywordsArray[s1], keywordsArray[s2]).size
            val sentence1 = annotatedSentences[s1].text
            val sentence2 = annotatedSentences[s2].text
            val s1Length = sentence1.length
            val s2Length = sentence2.length
            return when {
                s1Length <= 1 -> throw RuntimeException("Abnormal sentence: $sentence1")
                s2Length <= 1 -> throw RuntimeException("Abnormal sentence: $sentence2")
                else -> numCommon.toDouble() / (Math.log(s1Length.toDouble() +
                        Math.log(s2Length.toDouble())))
            }
        }

        /**
         * Compare the current salience value in [annotatedSentences] to a previous value to check
         * whether they are convergent.
         */
        private fun convergent(previousSalienceArray: DoubleArray): Boolean {
            for (i in previousSalienceArray.indices) {
                val diff = previousSalienceArray[i] - annotatedSentences[i].salience
                if (Math.abs(diff) > CONVERGENCE_THRESHOLD) {
                    return false
                }
            }
            return true
        }

        /**
         * [computeNewSalience] calculates and returns the new salience value among [num] sentences,
         * according to TextRank algorithm with respect to the current [startSentenceIndex].
         */
        private fun computeNewSalience(num: Int, startSentenceIndex: Int): Double {
            var sum = 0.0
            for (i in 0 until num) {
                if (i == startSentenceIndex) {
                    continue
                }
                val anotherSentence = annotatedSentences[i]
                val numerator = similarityMatrix[i][startSentenceIndex]
                var denominator = 0.0
                for (j in 0 until num) {
                    if (i == j) {
                        continue
                    }
                    denominator += similarityMatrix[i][j]
                }
                if (denominator < 0) {
                    throw RuntimeException("Bad addition!")
                }
                if (denominator > 1e-6) {
                    sum += numerator / denominator * anotherSentence.salience
                }
            }
            return (1 - D) + D * sum
        }

        /**
         * Use the Text Rank algorithm to randomly visit the sentence graph until the their
         * annotated salience value has converge.
         */
        fun randomVisit() {
            val num = annotatedSentences.size
            var startSentenceIndex = getRandomSentenceID()
            var start = annotatedSentences[startSentenceIndex]
            var counter = 0
            val previousSalienceArray = DoubleArray(size = num) { Short.MIN_VALUE.toDouble() }
            while (true) {
                if (counter % 100 == 0) {
                    // Check convergence every `num` times
                    if (convergent(previousSalienceArray = previousSalienceArray)) {
                        return
                    }
                    // Record for future use.
                    for (i in 0 until num) {
                        previousSalienceArray[i] = annotatedSentences[i].salience
                    }
                }
                // Update with a better salience value
                start.salience =
                        computeNewSalience(num = num, startSentenceIndex = startSentenceIndex)
                counter++
                // Randomly choose another sentence to continue the loop.
                val i = getRandomSentenceID(forbiddenValue = startSentenceIndex)
                startSentenceIndex = i
                start = annotatedSentences[i]
            }
        }

    }

    companion object {

        /**
         * Text Rank algorithm constant.
         */
        private const val D = 0.85
        /**
         * The threshold for convergence.
         */
        private const val CONVERGENCE_THRESHOLD = 1e-3

        /**
         * [getFromKey] returns a list of sentences with [limit] (defaults to 5) associated with
         * [textKey], with sentences sorted according to their sequence in the original article.
         */
        internal fun getFromKey(textKey: Key, limit: Int = 5): List<String> =
                SentenceEntity.query(ancestor = textKey) {
                    order { table.salience.desc() }
                    withLimit(limit = limit)
                }.sortedBy { it.beginOffset }.map { it.text }.toList()

        /**
         * [deleteAll] deletes all summaries for a given article with [articleKey].
         */
        internal fun deleteAll(articleKey: Key) {
            val keys = SentenceEntity.query(ancestor = articleKey).toList().map { it.key }
            SentenceEntity.delete(keys = keys)
        }

        /**
         * [get] returns a list of sentences with [limit] (defaults to 5) associated with [textKey],
         * with sentences sorted according to their sequence in the original article. If the given
         * [user] cannot access the article with [textKey], it will return an empty list.
         */
        operator fun get(user: GoogleUser, textKey: Key, limit: Int): List<String> =
                if (Article.userCanAccess(user = user, key = textKey)) {
                    getFromKey(textKey = textKey, limit = limit)
                } else {
                    emptyList()
                }

        /**
         * [markSentenceSalience] marks the salience of each sentence for a common [textKey],
         * [sentences] and [entities].
         */
        internal fun markSentenceSalience(
                textKey: Key, sentences: List<Sentence>, entities: List<LanguageEntity>
        ) {
            val source = SentenceSalienceMarker(sentences, entities)
                    .apply { randomVisit() }
                    .annotatedSentences
                    .toList()
            SentenceEntity.batchInsert(parent = textKey, source = source) { sentence ->
                table.beginOffset gets sentence.beginOffset
                table.salience gets sentence.salience
                table.text gets sentence.text
            }
        }

    }

}
