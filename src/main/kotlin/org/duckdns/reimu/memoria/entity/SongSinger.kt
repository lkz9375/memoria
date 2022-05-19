package org.duckdns.reimu.memoria.entity

import java.io.Serializable
import javax.persistence.*

data class SongSingerId(
    private val songId: Long = 0,
    private val singerId: Long = 0,
) : Serializable

/**
 * 하나의 노래는 여러 명이서 동시에 불릴 수 있음
 * 한 명의 가수는 여러 노래를 부를 수 있음
 */
@Entity
@Table(name = "song_singer")
@IdClass(SongSingerId::class)
class SongSinger(
    @Id
    val songId: Long,
    @Id
    val singerId: Long,
)
