package org.duckdns.reimu.memoria.model

import org.duckdns.reimu.memoria.entity.Music
import java.time.LocalDate

data class MusicDto(
    val url: String,
    val thumbnailUrl: String,
    val title: String,
    val artist: String,
    val length: Int,
    val uploaded: LocalDate
) {
    fun toEntity() = Music(
        url = url,
        thumbnailUrl = thumbnailUrl,
        title = title,
        artist = artist,
        length = length,
        uploaded = uploaded
    )
}
