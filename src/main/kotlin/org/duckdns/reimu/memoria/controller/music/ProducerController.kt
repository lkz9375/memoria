package org.duckdns.reimu.memoria.controller.music

import org.duckdns.reimu.memoria.service.ProducerService
import org.springframework.stereotype.Controller

@Controller
class ProducerController(
    private val producerService: ProducerService
)
