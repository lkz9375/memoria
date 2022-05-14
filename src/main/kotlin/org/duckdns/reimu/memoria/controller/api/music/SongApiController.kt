package org.duckdns.reimu.memoria.controller.api.music

import org.duckdns.reimu.memoria.entity.Music
import org.duckdns.reimu.memoria.model.param.AddMusicParam
import org.duckdns.reimu.memoria.model.param.UpdateMusicParam
import org.duckdns.reimu.memoria.service.MusicService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/music")
class SongApiController(
    private val musicService: MusicService,
) {
    @PostMapping("/song")
    fun addMusic(
        @RequestBody addMusicParam: AddMusicParam
    ): ResponseEntity<Music> {
        return ResponseEntity(
            musicService.add(addMusicParam),
            HttpStatus.CREATED
        )
    }

    @PutMapping("/song")
    fun updateMusic(
        @RequestBody updateMusicParam: UpdateMusicParam
    ): ResponseEntity<Music> {
        return ResponseEntity(
            musicService.update(updateMusicParam),
            HttpStatus.OK
        )
    }
}
