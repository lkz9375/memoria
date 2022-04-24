package org.duckdns.reimu.memoria.util

import org.duckdns.reimu.memoria.model.MusicDto
import org.jsoup.Jsoup
import java.time.LocalDate

class RequestUtils {
    companion object {
        fun makeMusicFrom(rawUrl: String): MusicDto {
            val document = Jsoup.connect(rawUrl).get()
            val div = document.getElementsByClass("watch-main-col")[0]

            lateinit var url: String
            lateinit var thumbnailUrl: String
            lateinit var title: String
            lateinit var artist: String
            lateinit var uploaded: LocalDate
            var length = 0

            div.childNodes().forEach { child ->
                when (child.attr("itemprop")) {
                    "url" -> url = child.attr("href")
                    "name" -> title = child.attr("content")
                    "thumbnailUrl" -> {
                        val rawThumbnailUrl = child.attr("href")
                        val thumbnailUrlRegex = """(.*/)""".toRegex()
                        thumbnailUrl = thumbnailUrlRegex.find(rawThumbnailUrl)!!.value + "hqdefault.jpg"
                    }
                    "datePublished" -> uploaded = LocalDate.parse(child.attr("content"))

                    "duration" -> {
                        val lengthRegex = """PT(\d+)M(\d+)S""".toRegex()
                        val (min, sec) = lengthRegex.find(child.attr("content"))!!.destructured
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
