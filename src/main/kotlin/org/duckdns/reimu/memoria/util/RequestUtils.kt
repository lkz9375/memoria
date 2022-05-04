package org.duckdns.reimu.memoria.util

import org.duckdns.reimu.memoria.enum.Site
import org.duckdns.reimu.memoria.model.MusicDto
import org.jsoup.Jsoup
import java.time.LocalDate

class RequestUtils {
    companion object {
        fun makeMusicFrom(rawUrl: String): MusicDto {
            return when {
                "youtu" in rawUrl -> makeMusicFromYoutube(rawUrl)
                else -> makeMusicFromNicovideo(rawUrl)
            }
        }

        private fun makeMusicFromYoutube(rawUrl: String): MusicDto {
            val document = Jsoup.connect(rawUrl).get()
            val div = document.getElementsByClass("watch-main-col")[0]

            lateinit var urlId: String
            lateinit var title: String
            lateinit var uploaded: LocalDate
            var length = 0

            div.childNodes().forEach { child ->
                when (child.attr("itemprop")) {
                    "name" -> title = child.attr("content")
                    "datePublished" -> uploaded = LocalDate.parse(child.attr("content"))
                    "url" -> {
                        val urlIdRegex = """v=(.+)$""".toRegex()
                        urlId = urlIdRegex.find(child.attr("href"))!!.groupValues[1]
                    }
                    "duration" -> {
                        val lengthRegex = """PT(\d+)M(\d+)S""".toRegex()
                        val (min, sec) = lengthRegex.find(child.attr("content"))!!.destructured
                        length = min.toInt() * 60 + sec.toInt()
                    }
                }
            }

            return MusicDto(
                urlId = urlId,
                site = Site.YOUTUBE,
                title = title,
                length = length,
                uploaded = uploaded
            )
        }

        private fun makeMusicFromNicovideo(rawUrl: String): MusicDto {
            val document = Jsoup.connect(rawUrl).get()
            val head = document.getElementsByTag("head")[0]

            lateinit var urlId: String
            lateinit var title: String
            lateinit var uploaded: LocalDate
            var length = 0

            head.childNodes().forEach { child ->
                if (child.nodeName() != "meta") {
                    return@forEach
                }

                when (child.attr("property")) {
                    "og:title" -> title = child.attr("content")
                    "video:duration" -> length = child.attr("content").toInt()
                    "og:url" -> {
                        val urlIdRegex = """(sm\d+)$""".toRegex()
                        urlId = urlIdRegex.find(child.attr("content"))!!.groupValues[0]
                    }
                    "video:release_date" -> {
                        val uploadedRegex = """^(\d+)-(\d+)-(\d+)T""".toRegex()
                        val (year, month, day) = uploadedRegex.find(child.attr("content"))!!.destructured
                        uploaded = LocalDate.of(year.toInt(), month.toInt(), day.toInt())
                    }
                }
            }

            return MusicDto(
                urlId = urlId,
                site = Site.NICOVIDEO,
                title = title,
                length = length,
                uploaded = uploaded
            )
        }
    }
}
