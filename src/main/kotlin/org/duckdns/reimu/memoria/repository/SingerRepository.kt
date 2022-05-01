package org.duckdns.reimu.memoria.repository

import org.duckdns.reimu.memoria.entity.Singer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SingerRepository : JpaRepository<Singer, Long>
