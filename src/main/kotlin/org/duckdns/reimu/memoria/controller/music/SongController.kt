package org.duckdns.reimu.memoria.controller.music

import org.duckdns.reimu.memoria.service.CommentService
import org.duckdns.reimu.memoria.service.SongService
import org.duckdns.reimu.memoria.service.SingerService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/music")
class SongController(
    private val songService: SongService,
    private val singerService: SingerService,
    private val commentService: CommentService,
) {
    @GetMapping("/songs")
    fun getMusicList(model: Model): String {
        val songList = songService.getList()
        val totalLength = songList.sumOf { music -> music.length }

        model.addAttribute("title", "Songs")
        model.addAttribute("songList", songList)
        model.addAttribute("length", totalLength)

        return "music/songs"
    }

    @GetMapping("/song/{songId}")
    fun getMusicDetail(
        @PathVariable(name = "songId") songId: Long = 0,
        model: Model
    ): String {
        val song = songService.get(songId)
        val singerList = singerService.getSingersByMusic(song)
        val commentList = commentService.getList(song.id)

        model.addAttribute("title", "Song Detail")
        model.addAttribute("song", song)
        model.addAttribute("singerList", singerList)
        model.addAttribute("commentList", commentList)

        return "music/songDetail"
    }
}
