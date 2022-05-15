package org.duckdns.reimu.memoria.repository

import org.duckdns.reimu.memoria.entity.Song
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SongRepository : JpaRepository<Song, Long> {
    fun findAllByOrderByIdDesc(): List<Song>
}
