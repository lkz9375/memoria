package org.duckdns.reimu.memoria.controller.music

import org.duckdns.reimu.memoria.service.SingerService
import org.duckdns.reimu.memoria.service.SongService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/music")
class SingerController(
    private val singerService: SingerService,
    private val songService: SongService,
) {
    @GetMapping("/singers")
    fun getSingerList(model: Model): String {
        val singerList = singerService.getList()
        val robotCount = singerList.count { it.isRobot }

        model.addAttribute("title", "Singers")
        model.addAttribute("singerList", singerList)
        model.addAttribute("robotCount", robotCount)

        return "music/singers"
    }

    @GetMapping("/singer/{singerId}")
    fun getSinger(
        @PathVariable(name = "singerId") singerId: Long,
        model: Model
    ): String {
        val singer = singerService.get(singerId)
        val songs = songService.getListBySinger(singer)

        model.addAttribute("title", singer.nameKorean)
        model.addAttribute("singer", singer)
        model.addAttribute("songs", songs)

        return "music/singerDetail"
    }
}
