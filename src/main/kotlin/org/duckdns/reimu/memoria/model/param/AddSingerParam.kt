package org.duckdns.reimu.memoria.model.param

data class AddSingerParam(
    val name: String,
    val nameKorean: String,
    val youtubeUrl: String? = null,
    val nicovideoUrl: String? = null,
    val isRobot: Boolean
)
