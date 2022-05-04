package org.duckdns.reimu.memoria.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class RequestUtilsTest {
    @ParameterizedTest
    @ValueSource(strings = [
        "https://www.youtube.com/watch?v=sFoWYa6QNKk&list=LL&index=79",
        "https://www.youtube.com/watch?v=sFoWYa6QNKk",
        "https://youtu.be/sFoWYa6QNKk"
    ])
    fun jsoupYoutubeTest(url: String) {
        val musicDto = RequestUtils.makeMusicFrom(url)

        assertEquals(123, musicDto.length)
        assertEquals("sFoWYa6QNKk", musicDto.urlId)
        assertEquals("『ぎゅって』feat. 初音ミク", musicDto.title)
        assertEquals("2022-04-23", musicDto.uploaded.toString())
    }

    @ParameterizedTest
    @ValueSource(strings = [
        "https://www.nicovideo.jp/watch/sm1097445",
    ])
    fun jsoupNicovideoTest(url: String) {
        val musicDto = RequestUtils.makeMusicFrom(url)

        assertEquals(99, musicDto.length)
        assertEquals("sm1097445", musicDto.urlId)
        assertEquals("【初音ミク】みくみくにしてあげる♪【してやんよ】", musicDto.title)
        assertEquals("2007-09-20", musicDto.uploaded.toString())
    }
}
