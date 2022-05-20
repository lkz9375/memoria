package org.duckdns.reimu.memoria.service

import org.duckdns.reimu.memoria.entity.Song
import org.duckdns.reimu.memoria.entity.SongProducer
import org.duckdns.reimu.memoria.entity.SongSinger
import org.duckdns.reimu.memoria.model.param.AddSongParam
import org.duckdns.reimu.memoria.model.param.UpdateSongParam
import org.duckdns.reimu.memoria.repository.SongProducerRepository
import org.duckdns.reimu.memoria.repository.SongRepository
import org.duckdns.reimu.memoria.repository.SongSingerRepository
import org.duckdns.reimu.memoria.util.RequestUtils
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException
import javax.transaction.Transactional

@Service
class SongService(
    private val songRepository: SongRepository,
    private val songSingerRepository: SongSingerRepository,
    private val songProducerRepository: SongProducerRepository,
) {
    fun getList(): List<Song> {
        return songRepository.findAllByOrderByIdDesc()
    }

    fun getListBySingerId(singerId: Long): List<Song> {
        return songRepository.findAllBySingerId(singerId)
    }

    fun getListByProducerId(producerId: Long): List<Song> {
        return songRepository.findAllByProducerId(producerId)
    }

    fun get(songId: Long): Song {
        return songRepository.findByIdOrNull(songId)
            ?: throw EntityNotFoundException("Unable to find org.duckdns.reimu.memoria.entity.Music with id $songId")
    }

    @Transactional
    fun add(addSongParam: AddSongParam): Song {
        val songDto = RequestUtils.makeMusicFrom(addSongParam.url)
        songDto.apply {
            parentId = addSongParam.parentId
            titleKorean = addSongParam.titleKorean
        }
        val song = songRepository.save(songDto.toEntity())

        songSingerRepository.saveAll(
            addSongParam.singerIds.map { singerId ->
                SongSinger(
                    songId = song.id,
                    singerId = singerId
                )
            }
        )

        songProducerRepository.saveAll(
            addSongParam.producerIds.map { producerId ->
                SongProducer(
                    songId = song.id,
                    producerId = producerId
                )
            }
        )

        return song
    }

    @Transactional
    fun update(updateSongParam: UpdateSongParam): Song {
        val music = songRepository.getById(updateSongParam.id)
        music.apply {
            parentId = updateSongParam.parentId
            titleKorean = updateSongParam.titleKorean
        }

        return music
    }
}
