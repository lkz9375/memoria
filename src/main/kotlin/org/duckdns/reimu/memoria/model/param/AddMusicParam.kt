package org.duckdns.reimu.memoria.model.param

data class AddMusicParam(
    val parentId: Long? = null,
    val url: String,
    val titleKorean: String,
    val singerIds: List<Long>,
)
