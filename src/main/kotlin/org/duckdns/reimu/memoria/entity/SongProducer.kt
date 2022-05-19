package org.duckdns.reimu.memoria.entity

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass
import javax.persistence.Table

data class SongProducerId(
    private val songId: Long = 0,
    private val producerId: Long = 0,
) : Serializable

@Entity
@Table(name = "song_producer")
@IdClass(SongProducerId::class)
class SongProducer(
    @Id
    val songId: Long,
    @Id
    val producerId: Long,
)
