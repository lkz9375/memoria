package org.duckdns.reimu.memoria.service

import org.duckdns.reimu.memoria.entity.Music
import org.duckdns.reimu.memoria.model.param.MusicParam
import org.duckdns.reimu.memoria.repository.MusicRepository
import org.duckdns.reimu.memoria.util.RequestUtils
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class MusicService(
    private val musicRepository: MusicRepository,
) {
    fun getList(): List<Music> {
        return musicRepository.findAllByOrderByIdDesc()
    }

    @Transactional
    fun add(musicParam: MusicParam): Music {
        val musicDto = RequestUtils.makeMusicFrom(musicParam.url)
        return musicRepository.save(musicDto.toEntity())
    }
}
