package org.duckdns.reimu.memoria.entity

import java.io.Serializable
import javax.persistence.*

data class MusicSingerId(
    private val musicId: Long = 0,
    private val singerId: Long = 0,
) : Serializable

/**
 * 하나의 노래는 여러 명이서 동시에 불릴 수 있음
 * 한 명의 가수는 여러 노래를 부를 수 있음
 */
@Entity
@Table(name = "musicSinger")
@IdClass(MusicSingerId::class)
class MusicSinger(
    @Id
    val musicId: Long,
    @Id
    val singerId: Long,
)
