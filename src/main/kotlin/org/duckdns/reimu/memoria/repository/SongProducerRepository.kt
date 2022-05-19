package org.duckdns.reimu.memoria.repository

import org.duckdns.reimu.memoria.entity.SongProducer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SongProducerRepository  : JpaRepository<SongProducer, Long>
