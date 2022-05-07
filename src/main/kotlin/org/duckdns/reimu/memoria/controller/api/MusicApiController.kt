package org.duckdns.reimu.memoria.controller.api

import org.duckdns.reimu.memoria.entity.Comment
import org.duckdns.reimu.memoria.entity.Music
import org.duckdns.reimu.memoria.entity.Singer
import org.duckdns.reimu.memoria.model.param.AddCommentParam
import org.duckdns.reimu.memoria.model.param.AddMusicParam
import org.duckdns.reimu.memoria.model.param.AddSingerParam
import org.duckdns.reimu.memoria.model.param.UpdateMusicParam
import org.duckdns.reimu.memoria.service.CommentService
import org.duckdns.reimu.memoria.service.MusicService
import org.duckdns.reimu.memoria.service.SingerService
import org.duckdns.reimu.memoria.util.RequestUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/music")
class MusicApiController(
    private val musicService: MusicService,
    private val singerService: SingerService,
    private val commentService: CommentService,
) {
    @PostMapping("song")
    fun addMusic(
        @RequestBody addMusicParam: AddMusicParam
    ): ResponseEntity<Music> {
        return ResponseEntity(
            musicService.add(addMusicParam),
            HttpStatus.CREATED
        )
    }

    @PutMapping("song")
    fun updateMusic(
        @RequestBody updateMusicParam: UpdateMusicParam
    ): ResponseEntity<Music> {
        return ResponseEntity(
            musicService.update(updateMusicParam),
            HttpStatus.CREATED
        )
    }

    @PostMapping("/singer")
    fun addSinger(
        @RequestBody addSingerParam: AddSingerParam
    ): ResponseEntity<Singer> {
        return ResponseEntity(
            singerService.add(addSingerParam),
            HttpStatus.CREATED
        )
    }

    @PostMapping("/{musicId}/comment")
    fun addComment(
        @PathVariable(name = "musicId") musicId: Long,
        @RequestBody addCommentParam: AddCommentParam,
        request: HttpServletRequest,
    ): ResponseEntity<Comment> {
        return ResponseEntity(
            commentService.add(musicId, RequestUtils.getIpAddress(request), addCommentParam),
            HttpStatus.CREATED
        )
    }

    @GetMapping("/{musicId}/comment")
    fun getCommentList(
        @PathVariable(name = "musicId") musicId: Long,
    ): ResponseEntity<List<Comment>> {
        return ResponseEntity(
            commentService.getList(musicId),
            HttpStatus.OK
        )
    }
}
