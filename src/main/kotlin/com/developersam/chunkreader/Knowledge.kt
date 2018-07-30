package com.developersam.chunkreader

import com.google.cloud.datastore.Entity
import com.google.cloud.datastore.Key
import typedstore.TypedEntity
import typedstore.TypedEntityCompanion
import typedstore.TypedTable
import com.google.cloud.language.v1beta2.Entity as LanguageEntity

/**
 * The [Knowledge] data class represents an entity that the user may have some interest in.
 *
 * @property name name of the knowledge keyword.
 * @property type type of the knowledge keyword.
 * @property url an optional Wikipedia link.
 * @property salience the importance of the field.
 */
internal data class Knowledge(
        private val name: String, private val type: Type,
        private val url: String?, private val salience: Double
) {

    /**
     * A collection of all known knowledge entity types.
     */
    enum class Type { PERSON, LOCATION, ORGANIZATION, EVENT, WORK_OF_ART, CONSUMER_GOOD, UNKNOWN }

    /**
     * [Table] is the table definition for [Knowledge].
     */
    private object Table : TypedTable<Table>(tableName = "ChunkReaderKnowledge") {
        val name = stringProperty(name = "name")
        val type = enumProperty(name = "type", clazz = Type::class.java)
        val url = nullableStringProperty(name = "url")
        val salience = doubleProperty(name = "salience")
    }

    private class KnowledgeEntity(entity: Entity) : TypedEntity<Table>(entity = entity) {
        val name: String = Table.name.delegatedValue
        val type: Type = Table.type.delegatedValue
        val url: String? = Table.url.delegatedValue
        val salience: Double = Table.salience.delegatedValue

        val asKnowledge: Knowledge
            get() = Knowledge(name = name, type = type, url = url, salience = salience)

        companion object : TypedEntityCompanion<Table, KnowledgeEntity>(table = Table) {
            override fun create(entity: Entity): KnowledgeEntity = KnowledgeEntity(entity = entity)
        }
    }

    companion object {

        /**
         * Convert an type from GCP to a [Type] in the system.
         */
        private fun LanguageEntity.Type.toType(): Type =
                when (this) {
                    LanguageEntity.Type.PERSON -> Type.PERSON
                    LanguageEntity.Type.LOCATION -> Type.LOCATION
                    LanguageEntity.Type.ORGANIZATION -> Type.ORGANIZATION
                    LanguageEntity.Type.EVENT -> Type.EVENT
                    LanguageEntity.Type.WORK_OF_ART -> Type.WORK_OF_ART
                    LanguageEntity.Type.CONSUMER_GOOD -> Type.CONSUMER_GOOD
                    LanguageEntity.Type.OTHER,
                    LanguageEntity.Type.UNKNOWN,
                    LanguageEntity.Type.UNRECOGNIZED -> Type.UNKNOWN
                }

        /**
         * [get] returns a map of categorized knowledge list for the given [textKey].
         */
        operator fun get(textKey: Key): Map<Type, List<Knowledge>> = KnowledgeEntity
                .query(ancestor = textKey) { order { table.salience.desc() } }
                .map { it.asKnowledge }
                .groupBy { it.type }
                .mapValues { (_, v) -> v.distinctBy { it.name } }

        /**
         * [deleteAll] deletes all knowledge for a given article with [articleKey].
         */
        internal fun deleteAll(articleKey: Key): Unit =
                KnowledgeEntity.queryKeys(ancestor = articleKey) {}
                        .toList()
                        .let { KnowledgeEntity.delete(it) }

        /**
         * [buildKnowledgeGraph] uses the information from [textKey] and [entities] to build the
         * knowledge graph for the given text.
         */
        fun buildKnowledgeGraph(textKey: Key, entities: List<LanguageEntity>) {
            KnowledgeEntity.batchInsert(parent = textKey, source = entities) { entity ->
                table.name gets entity.name
                table.type gets entity.type.toType()
                table.url gets entity.metadataMap["wikipedia_url"]
                table.salience gets entity.salience.toDouble()
            }
        }

    }

}
