package org.duckdns.reimu.memoria.model

import org.duckdns.reimu.memoria.entity.Song
import org.duckdns.reimu.memoria.enum.Site
import java.time.LocalDate

data class SongDto(
    var parentId: Long? = null,
    var titleKorean: String = "",
    val urlId: String,
    val site: Site,
    val title: String,
    val length: Int,
    val uploaded: LocalDate,
    val thumbnailUrl: String,
) {
    fun toEntity() = Song(
        parentId = parentId,
        urlId = urlId,
        site = site,
        title = title,
        titleKorean = titleKorean,
        length = length,
        uploaded = uploaded,
        thumbnailUrl = thumbnailUrl,
    )
}
