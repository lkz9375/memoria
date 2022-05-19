package org.duckdns.reimu.memoria.service

import org.duckdns.reimu.memoria.config.MultipartFileProps
import org.duckdns.reimu.memoria.entity.Producer
import org.duckdns.reimu.memoria.model.param.AddProducerParam
import org.duckdns.reimu.memoria.repository.ProducerRepository
import org.springframework.stereotype.Service
import java.io.File
import java.util.*
import javax.transaction.Transactional

@Service
class ProducerService(
    private val producerRepository: ProducerRepository,
    private val multipartFileProps: MultipartFileProps,
) {
    fun getList(songId: Long): List<Producer> {
        return producerRepository.findAllBySongId(songId)
    }

    @Transactional
    fun add(addProducerParam: AddProducerParam): Producer {
        val file = addProducerParam.file
        val filePath = file?.let {"/producer/${UUID.randomUUID()}_${file.originalFilename}" }

        file?.transferTo(File("${multipartFileProps.location}$filePath"))

        return producerRepository.save(
            Producer(
                name = addProducerParam.name,
                nameKorean = addProducerParam.nameKorean,
                thumbnail = filePath
            )
        )
    }
}
