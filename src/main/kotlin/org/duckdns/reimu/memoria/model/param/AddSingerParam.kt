package org.duckdns.reimu.memoria.model.param

import org.springframework.web.multipart.MultipartFile

data class AddSingerParam(
    val name: String,
    val nameKorean: String,
    val youtubeUrl: String? = null,
    val nicovideoUrl: String? = null,
    val isRobot: Boolean = false,
    val file: MultipartFile? = null,
)
