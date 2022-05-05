package org.duckdns.reimu.memoria.service

import org.duckdns.reimu.memoria.entity.Music
import org.duckdns.reimu.memoria.entity.MusicSinger
import org.duckdns.reimu.memoria.model.param.AddMusicParam
import org.duckdns.reimu.memoria.model.param.UpdateMusicParam
import org.duckdns.reimu.memoria.repository.MusicRepository
import org.duckdns.reimu.memoria.repository.MusicSingerRepository
import org.duckdns.reimu.memoria.util.RequestUtils
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class MusicService(
    private val musicRepository: MusicRepository,
    private val musicSingerRepository: MusicSingerRepository,
) {
    fun getList(): List<Music> {
        return musicRepository.findAllByOrderByIdDesc()
    }

    fun get(musicId: Long): Music {
        return musicRepository.getById(musicId)
    }

    @Transactional
    fun add(addMusicParam: AddMusicParam): Music {
        val musicDto = RequestUtils.makeMusicFrom(addMusicParam.url)
        musicDto.apply {
            parentId = addMusicParam.parentId
            titleKorean = addMusicParam.titleKorean
        }
        val music = musicRepository.save(musicDto.toEntity())

        musicSingerRepository.saveAll(
            addMusicParam.singerIds.map { singerId ->
                MusicSinger(
                    musicId = music.id,
                    singerId = singerId
                )
            }
        )

        return music
    }

    @Transactional
    fun update(updateMusicParam: UpdateMusicParam): Music {
        val music = musicRepository.getById(updateMusicParam.id)
        music.apply {
            parentId = updateMusicParam.parentId
            titleKorean = updateMusicParam.titleKorean
        }

        return music
    }
}
