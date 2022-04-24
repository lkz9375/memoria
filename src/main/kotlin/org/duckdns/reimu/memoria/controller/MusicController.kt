package org.duckdns.reimu.memoria.controller

import org.duckdns.reimu.memoria.service.MusicService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/music")
class MusicController(
    private val musicService: MusicService
) {
    @GetMapping("")
    fun getMusicList(
        model: Model
    ): String {
        val musicList = musicService.getList()
        model.addAttribute("musicList", musicList)

        return "music/index"
    }
}
