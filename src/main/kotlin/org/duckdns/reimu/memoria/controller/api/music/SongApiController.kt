package org.duckdns.reimu.memoria.controller.api.music

import org.duckdns.reimu.memoria.entity.Song
import org.duckdns.reimu.memoria.model.param.AddSongParam
import org.duckdns.reimu.memoria.model.param.UpdateSongParam
import org.duckdns.reimu.memoria.service.SongService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/music")
class SongApiController(
    private val songService: SongService,
) {
    @PostMapping("/song")
    fun addMusic(
        @RequestBody addSongParam: AddSongParam
    ): ResponseEntity<Song> {
        return ResponseEntity(
            songService.add(addSongParam),
            HttpStatus.CREATED
        )
    }

    @PutMapping("/song")
    fun updateMusic(
        @RequestBody updateSongParam: UpdateSongParam
    ): ResponseEntity<Song> {
        return ResponseEntity(
            songService.update(updateSongParam),
            HttpStatus.OK
        )
    }
}
