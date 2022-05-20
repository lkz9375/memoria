package org.duckdns.reimu.memoria.controller.music

import org.duckdns.reimu.memoria.service.ProducerService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/music")
class ProducerController(
    private val producerService: ProducerService
) {
    @GetMapping("/producers")
    fun getSingerList(model: Model): String {
        val producerList = producerService.getAllList()

        model.addAttribute("title", "Producers")
        model.addAttribute("producerList", producerList)

        return "music/producers"
    }
}
