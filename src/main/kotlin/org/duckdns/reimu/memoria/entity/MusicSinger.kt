package org.duckdns.reimu.memoria.entity

import javax.persistence.*

/**
 * 하나의 노래는 여러 명이서 동시에 불릴 수 있음
 * 한 명의 가수는 여러 노래를 부를 수 있음
 */
@Entity
@Table(name = "musicSinger")
class MusicSinger(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val musicId: Long,
    val singerId: Long,
)
