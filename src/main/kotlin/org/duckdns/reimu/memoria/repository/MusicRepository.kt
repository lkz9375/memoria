package org.duckdns.reimu.memoria.repository

import org.duckdns.reimu.memoria.entity.Music
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MusicRepository : JpaRepository<Music, Long> {
    fun findAllByOrderByIdDesc(): List<Music>
}
