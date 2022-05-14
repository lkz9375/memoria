package org.duckdns.reimu.memoria.controller.api.music

import org.duckdns.reimu.memoria.entity.Singer
import org.duckdns.reimu.memoria.model.param.AddSingerParam
import org.duckdns.reimu.memoria.service.SingerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/music")
class SingerApiController(
    private val singerService: SingerService,
) {
    @PostMapping("/singer")
    fun addSinger(
        @RequestBody addSingerParam: AddSingerParam
    ): ResponseEntity<Singer> {
        return ResponseEntity(
            singerService.add(addSingerParam),
            HttpStatus.CREATED
        )
    }
}
