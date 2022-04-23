package org.duckdns.reimu.memoria.util

import org.duckdns.reimu.memoria.model.MusicDto
import org.jsoup.Jsoup
import java.time.LocalDate

class RequestUtils {
    companion object {
        fun makeMusicFrom(url: String): MusicDto {
            val document = Jsoup.connect(url).get()
            val div = document.getElementsByClass("watch-main-col")[0]

            lateinit var thumbnailUrl: String
            lateinit var title: String
            lateinit var artist: String
            lateinit var uploaded: LocalDate
            var length = 0

            div.childNodes().forEach { child ->
                val content = child.attr("content")
                when (child.attr("itemprop")) {
                    "name" -> title = content
                    "thumbnailUrl" -> thumbnailUrl = content
                    "datePublished" -> uploaded = LocalDate.parse(content)

                    "duration" -> {
                        val lengthRegex = """PT(\d+)M(\d+)S""".toRegex()
                        val (min, sec) = lengthRegex.find(content)!!.destructured
                        length = min.toInt() * 60 + sec.toInt()
                    }

                    "author" -> {
                        val childs = child.childNodes()
                        val authorName = childs.find { it.attr("itemprop") == "name" }
                        artist = authorName?.attr("content")!!
                    }
                }
            }

            return MusicDto(
                url = url,
                thumbnailUrl = thumbnailUrl,
                title = title,
                artist = artist,
                length = length,
                uploaded = uploaded
            )
        }
    }
}
