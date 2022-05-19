package org.duckdns.reimu.memoria.repository

import org.duckdns.reimu.memoria.entity.Singer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface SingerRepository : JpaRepository<Singer, Long> {
    @Query(value =
    """
            SELECT *
            FROM singer
            WHERE id IN (
                SELECT singer_id
                FROM song_singer
                WHERE song_id = :songId
            )
            ORDER BY id
        """,
        nativeQuery = true
    )
    fun findAllBySongId(songId: Long): List<Singer>
}
