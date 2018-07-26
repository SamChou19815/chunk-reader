package com.developersam.chunkreader

import com.developersam.auth.GoogleUser
import kotlin.concurrent.thread

/**
 * A raw article input from user, which contains a [title] and [content].
 *
 * @property title title of the given raw article.
 * @property content content of the given raw article.
 */
data class RawArticle(val title: String = "", val content: String = "") {

    /**
     * [process] will starts to process the given [RawArticle] and returns immediately whether the
     * article is well-formatted.
     */
    fun process(user: GoogleUser): Boolean {
        if (title.isBlank() || content.isBlank()) {
            return false
        }
        thread(start = true) { Article.Processor.process(user = user, article = this) }
        return true
    }

}
