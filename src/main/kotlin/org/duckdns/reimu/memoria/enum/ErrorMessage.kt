package org.duckdns.reimu.memoria.enum

enum class ErrorMessage(
    val title: String,
    val body: String,
) {
    NO_SONG("노래 없음","찾으시는 노래가 존재하지 않습니다."),
    UNKNOWN_ERROR("오류 발생","알 수 없는 오류가 발생했습니다."),
    NO_PERMISSION("권한 없음","해당 페이지에 접근할 수 없습니다."),
}
