package org.duckdns.reimu.memoria.controller

import org.duckdns.reimu.memoria.service.MusicService
import org.duckdns.reimu.memoria.service.SingerService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/music")
class MusicController(
    private val musicService: MusicService,
    private val singerService: SingerService,
) {
    @GetMapping("")
    fun index(
        model: Model
    ): String {
        model.addAttribute("title", "Home")
        return "music/index"
    }

    @GetMapping("/songs")
    fun getMusicList(
        model: Model
    ): String {
        val musicList = musicService.getList()
        val totalLength = musicList.sumOf { music -> music.length }

        model.addAttribute("title", "Songs")
        model.addAttribute("musicList", musicList)
        model.addAttribute("length", totalLength)

        return "music/songs"
    }

    @GetMapping("/singers")
    fun getSingerList(
        model: Model
    ): String {
        val singerList = singerService.getList()
        val robotCount = singerList.count { it.isRobot }

        model.addAttribute("title", "Singers")
        model.addAttribute("singerList", singerList)
        model.addAttribute("robotCount", robotCount)

        return "music/singers"
    }
}
