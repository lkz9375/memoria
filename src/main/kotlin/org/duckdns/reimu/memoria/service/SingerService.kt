package org.duckdns.reimu.memoria.service

import org.duckdns.reimu.memoria.entity.Singer
import org.duckdns.reimu.memoria.model.param.AddSingerParam
import org.duckdns.reimu.memoria.repository.SingerRepository
import org.springframework.stereotype.Service

@Service
class SingerService(
    private val singerRepository: SingerRepository
) {
    fun getList(): List<Singer> {
        return singerRepository.findAll()
    }

    fun add(addSingerParam: AddSingerParam): Singer {
        return singerRepository.save(
            Singer(
                name = addSingerParam.name,
                nameKorean = addSingerParam.nameKorean,
                youtubeUrl = addSingerParam.youtubeUrl,
                nicovideoUrl = addSingerParam.nicovideoUrl,
                isRobot = addSingerParam.isRobot
            )
        )
    }
}
