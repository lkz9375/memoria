package org.duckdns.reimu.memoria.entity

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "music")
class Music(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val url: String,
    val thumbnailUrl: String,
    val title: String,
    val artist: String,
    val length: Int,
    val uploaded: LocalDate
)
