package org.duckdns.reimu.memoria.controller.music

import org.duckdns.reimu.memoria.model.param.AddProducerParam
import org.duckdns.reimu.memoria.model.param.AddSongParam
import org.duckdns.reimu.memoria.model.param.AddSingerParam
import org.duckdns.reimu.memoria.service.ProducerService
import org.duckdns.reimu.memoria.service.SongService
import org.duckdns.reimu.memoria.service.SingerService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.util.WebUtils
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("/music/admin")
class AdminController(
    private val songService: SongService,
    private val singerService: SingerService,
    private val producerService: ProducerService,
) {
    @GetMapping
    fun manager(
        model: Model,
        request: HttpServletRequest,
    ): String {
        val cookie = WebUtils.getCookie(request, "admin")
        if (cookie == null || cookie.value != "ReimuHakurei") {
            throw SecurityException("권한이 부족합니다.")
        }

        model.addAttribute("title", "Admin")
        model.addAttribute("active", 0)

        return "music/admin"
    }

    @PostMapping("/singer")
    fun addSinger(
        request: HttpServletRequest,
        addSingerParam: AddSingerParam,
    ): String {
        val cookie = WebUtils.getCookie(request, "admin")
        if (cookie == null || cookie.value != "ReimuHakurei") {
            throw SecurityException("권한이 부족합니다.")
        }

        singerService.add(addSingerParam)

        return "redirect:/music/admin"
    }

    @PostMapping("/song")
    fun addSong(
        request: HttpServletRequest,
        addSongParam: AddSongParam,
    ): String {
        val cookie = WebUtils.getCookie(request, "admin")
        if (cookie == null || cookie.value != "ReimuHakurei") {
            throw SecurityException("권한이 부족합니다.")
        }

        songService.add(addSongParam)

        return "redirect:/music/admin"
    }

    @PostMapping("/producer")
    fun addProducer(
        request: HttpServletRequest,
        addProducerParam: AddProducerParam,
    ): String {
        val cookie = WebUtils.getCookie(request, "admin")
        if (cookie == null || cookie.value != "ReimuHakurei") {
            throw SecurityException("권한이 부족합니다.")
        }

        producerService.add(addProducerParam)

        return "redirect:/music/admin"
    }
}
