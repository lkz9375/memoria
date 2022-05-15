package org.duckdns.reimu.memoria.controller.music

import org.duckdns.reimu.memoria.service.CommentService
import org.duckdns.reimu.memoria.service.MusicService
import org.duckdns.reimu.memoria.service.SingerService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/music")
class SongController(
    private val musicService: MusicService,
    private val singerService: SingerService,
    private val commentService: CommentService,
) {
    @GetMapping("/songs")
    fun getMusicList(model: Model): String {
        val musicList = musicService.getList()
        val totalLength = musicList.sumOf { music -> music.length }

        model.addAttribute("title", "Songs")
        model.addAttribute("musicList", musicList)
        model.addAttribute("length", totalLength)

        return "music/songs"
    }

    @GetMapping("/song/{musicId}")
    fun getMusicDetail(
        @PathVariable(name = "musicId") musicId: Long = 0,
        model: Model
    ): String {
        val music = musicService.get(musicId)
        val singerList = singerService.getSingersByMusic(music)
        val commentList = commentService.getList(music.id)

        model.addAttribute("title", "Song Detail")
        model.addAttribute("music", music)
        model.addAttribute("singerList", singerList)
        model.addAttribute("commentList", commentList)

        return "music/songDetail"
    }
}