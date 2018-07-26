package com.developersam.chunkreader

import com.developersam.auth.SecurityFilters.Companion.user
import com.developersam.web.badRequest
import com.developersam.web.delete
import com.developersam.web.get
import com.developersam.web.post
import com.developersam.web.queryParamsForKey
import com.developersam.web.toJson

/**
 * [ApiHandlers] provides a set of API handlers to the server.
 */
object ApiHandlers {

    /**
     * [initializeChunkReaderApiHandlers] initializes a list of Chunk Reader API handlers.
     */
    @JvmStatic
    fun initializeChunkReaderApiHandlers() {
        get(path = "/load") { Article[user] }
        post(path = "/analyze") { toJson<RawArticle>().process(user = user) }
        get(path = "/adjust_summary") {
            val key = queryParamsForKey(name = "key")
            val limit = queryParams("limit")?.toInt() ?: badRequest()
            Summary[user, key, limit]
        }
        delete(path = "/delete") {
            val key = queryParamsForKey(name = "key")
            Article.delete(user = user, key = key)
        }
    }

}
