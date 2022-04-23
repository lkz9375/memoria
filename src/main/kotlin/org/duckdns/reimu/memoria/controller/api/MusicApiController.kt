package org.duckdns.reimu.memoria.controller.api

import org.duckdns.reimu.memoria.entity.Music
import org.duckdns.reimu.memoria.model.param.MusicParam
import org.duckdns.reimu.memoria.service.MusicService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/music")
class MusicApiController(
    private val musicService: MusicService
) {
    @PostMapping
    fun addMusic(
        @RequestBody musicParam: MusicParam
    ): ResponseEntity<Music> {
        return ResponseEntity(
            musicService.add(musicParam),
            HttpStatus.CREATED
        )
    }
}
