package org.duckdns.reimu.memoria.controller.api.music

import org.duckdns.reimu.memoria.entity.Comment
import org.duckdns.reimu.memoria.model.CommentDto
import org.duckdns.reimu.memoria.model.param.AddCommentParam
import org.duckdns.reimu.memoria.model.param.DeleteCommentParam
import org.duckdns.reimu.memoria.service.CommentService
import org.duckdns.reimu.memoria.util.RequestUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/comment")
class CommentApiController(
    private val commentService: CommentService,
) {
    @PostMapping("/{musicId}")
    fun addComment(
        @PathVariable(name = "musicId") musicId: Long,
        @RequestBody addCommentParam: AddCommentParam,
        request: HttpServletRequest,
    ): ResponseEntity<Comment> {
        return ResponseEntity(
            commentService.add(musicId, RequestUtils.getIpAddress(request), addCommentParam),
            HttpStatus.CREATED
        )
    }

    @DeleteMapping("/{commentId}")
    fun deleteComment(
        @PathVariable(name = "commentId") commentId: Long,
        @RequestBody deleteCommentParam: DeleteCommentParam,
        request: HttpServletRequest,
    ): ResponseEntity<Boolean> {
        val result = commentService.delete(commentId, deleteCommentParam)
        return ResponseEntity(
            result,
            if (result) HttpStatus.OK else HttpStatus.BAD_REQUEST
        )
    }

    @GetMapping("/{musicId}")
    fun getCommentList(
        @PathVariable(name = "musicId") musicId: Long,
    ): ResponseEntity<List<CommentDto>> {
        val commentList = commentService.getList(musicId)
        val commentDtoList = commentList.map {
            CommentDto(
                id = it.id,
                body = it.body,
                nickname = it.nickname,
                ip = it.ip,
                created = it.created
            )
        }

        return ResponseEntity(
            commentDtoList,
            HttpStatus.OK
        )
    }
}
