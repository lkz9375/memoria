package org.duckdns.reimu.memoria.repository

import org.duckdns.reimu.memoria.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository : JpaRepository<Comment, Long> {
    fun findAllByMusicIdOrderByCreatedDesc(musicId: Long): List<Comment>
}
