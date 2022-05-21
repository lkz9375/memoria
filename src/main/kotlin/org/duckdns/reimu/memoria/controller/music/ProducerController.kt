package org.duckdns.reimu.memoria.controller.music

import org.duckdns.reimu.memoria.service.ProducerService
import org.duckdns.reimu.memoria.service.SongService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/music")
class ProducerController(
    private val producerService: ProducerService,
    private val songService: SongService,
) {
    @GetMapping("/producers")
    fun getProducerList(model: Model): String {
        val producerList = producerService.getAllList()

        model.addAttribute("title", "Producers")
        model.addAttribute("active", 4)
        model.addAttribute("producerList", producerList)

        return "music/producers"
    }

    @GetMapping("/producer/{producerId}")
    fun getProducer(
        @PathVariable(name = "producerId") producerId: Long,
        model: Model
    ): String {
        val producer = producerService.get(producerId)
        val songs = songService.getListByProducerId(producer.id)

        model.addAttribute("title", "Producers")
        model.addAttribute("producer", producer)
        model.addAttribute("songs", songs)

        return "music/producerDetail"
    }
}
