package org.duckdns.reimu.memoria.util

import org.duckdns.reimu.memoria.enum.Site
import org.duckdns.reimu.memoria.model.SongDto
import org.jsoup.Jsoup
import java.time.LocalDate
import javax.servlet.http.HttpServletRequest

class RequestUtils {
    companion object {
        fun getIpAddress(request: HttpServletRequest): String {
            return request.getHeader("X-FORWARDED-FOR") ?: request.remoteAddr
        }

        fun makeMusicFrom(rawUrl: String): SongDto {
            return when {
                "youtu" in rawUrl -> makeMusicFromYoutube(rawUrl)
                else -> makeMusicFromNicovideo(rawUrl)
            }
        }

        private fun makeMusicFromYoutube(rawUrl: String): SongDto {
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

            return SongDto(
                urlId = urlId,
                site = Site.YOUTUBE,
                title = title,
                length = length,
                uploaded = uploaded,
                thumbnailUrl = "https://i.ytimg.com/vi/$urlId/hqdefault.jpg",
            )
        }

        private fun makeMusicFromNicovideo(rawUrl: String): SongDto {
            val document = Jsoup.connect(rawUrl).get()
            val head = document.getElementsByTag("head")[0]

            lateinit var urlId: String
            lateinit var title: String
            lateinit var uploaded: LocalDate
            lateinit var thumbnailUrl: String
            var length = 0

            head.childNodes().forEach { child ->
                if (child.nodeName() != "meta") {
                    return@forEach
                }

                when (child.attr("property")) {
                    "og:title" -> title = child.attr("content")
                    "video:duration" -> length = child.attr("content").toInt()
                    "og:image" -> {
                        val thumbnailUrlRegex = """.+?(thumbnails/.+?)\.original""".toRegex()
                        thumbnailUrl = "https://nicovideo.cdn.nimg.jp/" + thumbnailUrlRegex.find(child.attr("content"))!!.groupValues[1]
                    }
                    "og:url" -> {
                        val urlIdRegex = """([^/]+)$""".toRegex()
                        urlId = urlIdRegex.find(child.attr("content"))!!.groupValues[0]
                    }
                    "video:release_date" -> {
                        val uploadedRegex = """^(\d+)-(\d+)-(\d+)T""".toRegex()
                        val (year, month, day) = uploadedRegex.find(child.attr("content"))!!.destructured
                        uploaded = LocalDate.of(year.toInt(), month.toInt(), day.toInt())
                    }
                }
            }

            return SongDto(
                urlId = urlId,
                site = Site.NICOVIDEO,
                title = title,
                length = length,
                uploaded = uploaded,
                thumbnailUrl = thumbnailUrl,
            )
        }
    }
}
