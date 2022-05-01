package org.duckdns.reimu.memoria.entity

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass
import javax.persistence.Table

data class LoveId(
    private val musicId: Long,
    private val ip: String,
) : Serializable

@Entity
@Table(name = "love")
@IdClass(LoveId::class)
class Love(
    @Id
    private val musicId: Long,
    @Id
    private val ip: String,
)
