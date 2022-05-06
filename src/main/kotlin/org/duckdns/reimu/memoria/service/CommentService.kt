package org.duckdns.reimu.memoria.service

import org.duckdns.reimu.memoria.entity.Comment
import org.duckdns.reimu.memoria.model.param.AddCommentParam
import org.duckdns.reimu.memoria.repository.CommentRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.transaction.Transactional

@Service
class CommentService(
    private val commentRepository: CommentRepository
) {
    fun getList(musicId: Long): List<Comment> {
        return commentRepository.findAllByMusicIdOrderByCreatedDesc(musicId)
    }

    @Transactional
    fun add(musicId: Long, ip: String, addCommentParam: AddCommentParam): Comment {
        if (addCommentParam.nickname.length > 20) {
            throw IllegalArgumentException("comment nickname length must be below 20")
        }
        if (addCommentParam.password.length > 32) {
            throw IllegalArgumentException("comment password length must be below 32")
        }
        if (addCommentParam.body.length > 200) {
            throw IllegalArgumentException("comment body length must be below 200")
        }

        return commentRepository.save(
            Comment(
                nickname = addCommentParam.nickname,
                password = addCommentParam.password,
                body = addCommentParam.body,
                ip = ip,
                musicId = musicId,
                created = LocalDateTime.now()
            )
        )
    }
}
