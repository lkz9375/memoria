package org.duckdns.reimu.memoria.model.param

data class AddSongParam(
    val parentId: Long? = null,
    val url: String,
    val titleKorean: String,
    val singerIds: List<Long>,
    val producerIds: List<Long>,
)
