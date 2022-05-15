package org.duckdns.reimu.memoria.controller.music

import org.duckdns.reimu.memoria.model.param.AddMusicParam
import org.duckdns.reimu.memoria.model.param.AddSingerParam
import org.duckdns.reimu.memoria.service.MusicService
import org.duckdns.reimu.memoria.service.SingerService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.util.WebUtils
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("/music/admin")
class AdminController(
    private val musicService: MusicService,
    private val singerService: SingerService,
) {
    @GetMapping
    fun manager(request: HttpServletRequest): String {
        val cookie = WebUtils.getCookie(request, "admin")
        if (cookie == null || cookie.value != "ReimuHakurei") {
            throw SecurityException("권한이 부족합니다.")
        }

        return "music/admin"
    }

    @PostMapping("/singer")
    fun upload(
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
    fun upload(
        request: HttpServletRequest,
        addMusicParam: AddMusicParam,
    ): String {
        val cookie = WebUtils.getCookie(request, "admin")
        if (cookie == null || cookie.value != "ReimuHakurei") {
            throw SecurityException("권한이 부족합니다.")
        }

        musicService.add(addMusicParam)

        return "redirect:/music/admin"
    }
}
