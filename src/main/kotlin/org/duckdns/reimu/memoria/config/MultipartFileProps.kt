package org.duckdns.reimu.memoria.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "spring.servlet.multipart")
data class MultipartFileProps(
    var location: String = ""
)
