package org.duckdns.reimu.memoria.model

import java.time.LocalDateTime

data class CommentDto(
    val id: Long,
    val body: String,
    val nickname: String,
    val ip: String,
    val created: LocalDateTime,
)
