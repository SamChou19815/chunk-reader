package com.developersam.chunkreader

import com.google.cloud.language.v1beta2.AnalyzeSyntaxResponse
import com.google.cloud.language.v1beta2.Document
import com.google.cloud.language.v1beta2.EncodingType
import com.google.cloud.language.v1beta2.Entity
import com.google.cloud.language.v1beta2.LanguageServiceClient
import com.google.cloud.language.v1beta2.Sentence
import java.util.concurrent.CountDownLatch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * A [NlpApiAnalyzer] using Google Cloud NLP API directly.
 *
 * It should be constructed from the text needed to analyze.
 */
internal class NlpApiAnalyzer(text: String) {

    /**
     * [entities] is a list of entities extracted from the text.
     */
    val entities: List<Entity>
    /**
     * [sentences] is a list of sentences extracted from the text.
     */
    val sentences: List<Sentence>
    /**
     * [tokenCount] is number of tokens in the sentence.
     */
    val tokenCount: Int

    /**
     * [service] is the thread poll for this analyzer.
     */
    private val service = Executors.newFixedThreadPool(3)
    /**
     * [latch] is the latch for coordination.
     */
    private val latch = CountDownLatch(2)

    init {
        val client = LanguageServiceClient.create()
        try {
            val doc: Document = Document.newBuilder()
                    .setContent(text).setType(Document.Type.PLAIN_TEXT).build()
            var entities = emptyList<Entity>()
            var sentences = emptyList<Sentence>()
            var tokenCount = 0
            service.submitWithCountdown {
                val temp = client.analyzeEntitySentiment(doc, EncodingType.UTF16).entitiesList
                synchronized(client) {
                    entities = temp
                }
            }
            service.submitWithCountdown {
                val r: AnalyzeSyntaxResponse = client.analyzeSyntax(doc, EncodingType.UTF16)
                synchronized(client) {
                    sentences = r.sentencesList
                    tokenCount = r.tokensCount
                }
            }
            latch.await()
            this.entities = entities
            this.sentences = sentences
            this.tokenCount = tokenCount
        } finally {
            client.close()
            service.shutdown()
        }
    }

    /**
     * [ExecutorService.submitWithCountdown] execute [f] with this [ExecutorService] and make the
     * latch count down by one.
     */
    private inline fun ExecutorService.submitWithCountdown(crossinline f: () -> Unit) {
        submit { f(); latch.countDown() }
    }

}
