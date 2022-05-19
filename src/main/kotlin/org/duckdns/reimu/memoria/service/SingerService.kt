package org.duckdns.reimu.memoria.service

import org.duckdns.reimu.memoria.config.MultipartFileProps
import org.duckdns.reimu.memoria.entity.Singer
import org.duckdns.reimu.memoria.model.param.AddSingerParam
import org.duckdns.reimu.memoria.repository.SingerRepository
import org.springframework.stereotype.Service
import java.io.File
import java.util.*
import javax.transaction.Transactional

@Service
class SingerService(
    private val singerRepository: SingerRepository,
    private val multipartFileProps: MultipartFileProps,
) {
    fun getList(): List<Singer> {
        return singerRepository.findAll()
    }

    fun get(singerId: Long): Singer {
        return singerRepository.getById(singerId)
    }

    fun getSingersBySongId(songId: Long): List<Singer> {
        return singerRepository.findAllBySongId(songId)
    }

    @Transactional
    fun add(addSingerParam: AddSingerParam): Singer {
        val file = addSingerParam.file
        val filePath = file?.let { "/singer/${UUID.randomUUID()}_${file.originalFilename}" }

        file?.transferTo(File("${multipartFileProps.location}$filePath"))

        return singerRepository.save(
            Singer(
                name = addSingerParam.name,
                nameKorean = addSingerParam.nameKorean,
                youtubeUrl = addSingerParam.youtubeUrl,
                nicovideoUrl = addSingerParam.nicovideoUrl,
                isRobot = addSingerParam.isRobot,
                thumbnail = filePath
            )
        )
    }
}
