package org.duckdns.reimu.memoria.repository

import org.duckdns.reimu.memoria.entity.MusicSinger
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MusicSingerRepository : JpaRepository<MusicSinger, Long>
