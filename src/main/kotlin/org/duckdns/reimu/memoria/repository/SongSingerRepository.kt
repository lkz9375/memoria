package org.duckdns.reimu.memoria.repository

import org.duckdns.reimu.memoria.entity.SongSinger
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SongSingerRepository : JpaRepository<SongSinger, Long> {
    fun findAllBySongId(songId: Long): List<SongSinger>

    fun findAllBySingerId(singerId: Long): List<SongSinger>
}
