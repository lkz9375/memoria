package org.duckdns.reimu.memoria.entity

import org.duckdns.reimu.memoria.enum.Site
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "music")
class Music(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    /**
     * Youtube -> watch?v={UuEwifLH0eg}
     * Nicovideo -> watch/{sm40378252}
     */
    @Column(unique = true)
    val urlId: String,

    @Enumerated(EnumType.STRING)
    val site: Site,

    val title: String,
    var parentId: Long? = null,
    var titleKorean: String,
    var length: Int,
    var uploaded: LocalDate,
    val thumbnailUrl: String,
) {
    val url: String
        get() = when (site) {
            Site.YOUTUBE -> "https://www.youtube.com/watch?v=$urlId"
            else -> "https://www.nicovideo.jp/watch/$urlId"
        }

    val embedTag: String
        get() = when (site) {
            Site.YOUTUBE -> "https://www.youtube.com/embed/$urlId"
            else -> "https://embed.nicovideo.jp/watch/$urlId"
        }

    fun isYoutube() = site == Site.YOUTUBE
}
