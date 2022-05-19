package org.duckdns.reimu.memoria.repository

import org.duckdns.reimu.memoria.entity.Producer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ProducerRepository : JpaRepository<Producer, Long> {
    @Query(value =
        """
            SELECT *
            FROM producer
            WHERE id IN (
                SELECT producer_id
                FROM song_producer
                WHERE song_id = :songId
            )
            ORDER BY id
        """,
        nativeQuery = true
    )
    fun findAllBySongId(songId: Long): List<Producer>
}
