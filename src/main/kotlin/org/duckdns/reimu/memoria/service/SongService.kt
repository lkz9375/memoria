package org.duckdns.reimu.memoria.service

import org.duckdns.reimu.memoria.entity.Singer
import org.duckdns.reimu.memoria.entity.Song
import org.duckdns.reimu.memoria.entity.SongSinger
import org.duckdns.reimu.memoria.model.param.AddSongParam
import org.duckdns.reimu.memoria.model.param.UpdateSongParam
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
) {
    fun getList(): List<Song> {
        return songRepository.findAllByOrderByIdDesc()
    }

    fun getListBySinger(singer: Singer): List<Song> {
        val songsBySinger = songSingerRepository.findAllBySingerId(singer.id)
        return songRepository.findAllById(songsBySinger.map { it.songId }).reversed()
    }

    fun get(musicId: Long): Song {
        return songRepository.findByIdOrNull(musicId)
            ?: throw EntityNotFoundException("Unable to find org.duckdns.reimu.memoria.entity.Music with id $musicId")
    }

    @Transactional
    fun add(addSongParam: AddSongParam): Song {
        val musicDto = RequestUtils.makeMusicFrom(addSongParam.url)
        musicDto.apply {
            parentId = addSongParam.parentId
            titleKorean = addSongParam.titleKorean
        }
        val music = songRepository.save(musicDto.toEntity())

        songSingerRepository.saveAll(
            addSongParam.singerIds.map { singerId ->
                SongSinger(
                    songId = music.id,
                    singerId = singerId
                )
            }
        )

        return music
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
