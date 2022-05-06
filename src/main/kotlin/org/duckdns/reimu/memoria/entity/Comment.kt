package org.duckdns.reimu.memoria.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "comment")
class Comment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(length = 500)
    val body: String,

    val nickname: String,
    val password: String,
    val ip: String,
    val musicId: Long,
    val created: LocalDateTime,
)
