package org.duckdns.reimu.memoria.entity

import javax.persistence.*

@Entity
@Table(name = "producer")
class Producer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    /**
     * 모두 소문자로 적을 것
     * e.g.
     * - deco*27
     * - nanawoakari
     */
    @Column(unique = true)
    val name: String,

    val nameKorean: String,
    val thumbnail: String?,
)
