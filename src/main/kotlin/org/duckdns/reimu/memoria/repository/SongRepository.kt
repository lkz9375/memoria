package org.duckdns.reimu.memoria.repository

import org.duckdns.reimu.memoria.entity.Song
import org.duckdns.reimu.memoria.model.SongMetadataDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface SongRepository : JpaRepository<Song, Long> {
    fun findAllByOrderByIdDesc(): List<Song>

    fun findAllByOrderByIdDesc(pageable: Pageable): Page<Song>

    @Query(value =
        """
            SELECT
                COUNT(*) AS count,
                SUM(length) AS totalLength
            FROM song
        """,
        nativeQuery = true
    )
    fun calcMetadata(): SongMetadataDto

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

    @Query(value =
        """
            SELECT *
            FROM song
            WHERE id IN (
                SELECT song_id
                FROM song_producer
                WHERE producer_id = :producerId
            )
            ORDER BY uploaded DESC
        """,
        nativeQuery = true
    )
    fun findAllByProducerId(producerId: Long): List<Song>
}
