package org.duckdns.reimu.memoria.model.param

import org.springframework.web.multipart.MultipartFile

data class AddProducerParam(
    val name: String,
    val nameKorean: String,
    val file: MultipartFile? = null,
)
