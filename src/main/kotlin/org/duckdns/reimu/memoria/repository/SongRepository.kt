package org.duckdns.reimu.memoria.repository

import org.duckdns.reimu.memoria.entity.Song
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface SongRepository : JpaRepository<Song, Long> {
    fun findAllByOrderByIdDesc(): List<Song>

    @Query(value =
        """
            SELECT *
            FROM song
            WHERE id IN (
                SELECT song_id
                FROM song_singer
                WHERE singer_id = :singerId
            )
            ORDER BY uploaded DESC
        """,
        nativeQuery = true
    )
    fun findAllBySingerId(singerId: Long): List<Song>
}
