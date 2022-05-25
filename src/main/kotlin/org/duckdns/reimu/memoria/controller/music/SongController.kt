package org.duckdns.reimu.memoria.controller.music

import org.duckdns.reimu.memoria.service.CommentService
import org.duckdns.reimu.memoria.service.ProducerService
import org.duckdns.reimu.memoria.service.SongService
import org.duckdns.reimu.memoria.service.SingerService
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/music")
class SongController(
    private val songService: SongService,
    private val singerService: SingerService,
    private val commentService: CommentService,
    private val producerService: ProducerService,
) {
    @GetMapping("/songs")
    fun getSongList(
        @RequestParam(name = "page", required = false, defaultValue = "1") page: Int,
        @RequestParam(name = "size", required = false, defaultValue = "24") size: Int,
        model: Model
    ): String {
        val songPage = songService.getPage(PageRequest.of(page - 1, size))
        val metadata = songService.getMetadata()

        model.addAttribute("title", "Songs")
        model.addAttribute("active", 2)
        model.addAttribute("songPage", songPage)
        model.addAttribute("metadata", metadata)
        model.addAttribute("size", size)

        return "music/songs"
    }

    @GetMapping("/song/{songId}")
    fun getSongDetail(
        @PathVariable(name = "songId") songId: Long = 0,
        model: Model
    ): String {
        val song = songService.get(songId)
        val singerList = singerService.getSingersBySongId(song.id)
        val commentList = commentService.getList(song.id)
        val producerList = producerService.getList(song.id)

        model.addAttribute("title", "${singerList[0].nameKorean}${if (singerList.size > 1) "(외 ${singerList.size - 1}명)" else ""} - ${song.titleKorean}")
        model.addAttribute("song", song)
        model.addAttribute("singerList", singerList)
        model.addAttribute("commentList", commentList)
        model.addAttribute("producerList", producerList)

        return "music/songDetail"
    }
}
