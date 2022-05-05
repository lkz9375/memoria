package org.duckdns.reimu.memoria.controller

import mu.KLogging
import org.duckdns.reimu.memoria.enum.ErrorMessage
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.persistence.EntityNotFoundException
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ControllerErrorAdvice {
    companion object : KLogging()

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFoundException(
        e: EntityNotFoundException,
        model: Model,
        request: HttpServletRequest,
    ): String {
        logger.error { """
            [EntityNotFoundException]
            -- requestURI : ${request.requestURI}
            -- message : ${e.message}
            """.trimIndent()
        }

        model.addAttribute("title", "Error")
        model.addAttribute("errorMessage", ErrorMessage.NO_SONG)

        return "music/error"
    }

    @ExceptionHandler(Exception::class)
    fun handleException(
        e: Exception,
        model: Model,
        request: HttpServletRequest,
    ): String {
        logger.error { """
            [Exception]
            -- requestURI : ${request.requestURI}
            -- message : ${e.message}
            """.trimIndent()
        }

        model.addAttribute("title", "Error")
        model.addAttribute("errorMessage", ErrorMessage.UNKNOWN_ERROR)

        return "music/error"
    }
}
