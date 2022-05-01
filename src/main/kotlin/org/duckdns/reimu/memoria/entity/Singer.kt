package org.duckdns.reimu.memoria.entity

import javax.persistence.*

@Entity
@Table(name = "singer")
class Singer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    /**
     * 모두 소문자로 적을 것
     * e.g.
     * - hatsune miku
     * - yonezu kenshi
     */
    @Column(unique = true)
    val name: String,

    val nameKorean: String,
    val youtubeUrl: String?,
    val nicovideoUrl: String?,
    val isRobot: Boolean,
)
