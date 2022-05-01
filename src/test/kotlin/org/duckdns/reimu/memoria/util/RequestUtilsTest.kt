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
    fun jsoupTest(url: String) {
        val musicDto = RequestUtils.makeMusicFrom(url)

        assertEquals(123, musicDto.length)
        assertEquals("sFoWYa6QNKk", musicDto.urlId)
        assertEquals("『ぎゅって』feat. 初音ミク", musicDto.title)
        assertEquals("2022-04-23", musicDto.uploaded.toString())
    }
}
