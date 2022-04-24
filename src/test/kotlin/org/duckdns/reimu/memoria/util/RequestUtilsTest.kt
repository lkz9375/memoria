package org.duckdns.reimu.memoria.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class RequestUtilsTest {
    @Test
    fun jsoupTest1() {
        val url = "https://www.youtube.com/watch?v=sFoWYa6QNKk&list=LL&index=79"
        val musicDto = RequestUtils.makeMusicFrom(url)

        assertEquals(123, musicDto.length)
        assertEquals("https://www.youtube.com/watch?v=sFoWYa6QNKk", musicDto.url)
        assertEquals("『ぎゅって』feat. 初音ミク", musicDto.title)
        assertEquals("MIMI", musicDto.artist)
        assertEquals("2022-04-23", musicDto.uploaded.toString())
        assertEquals("https://i.ytimg.com/vi/sFoWYa6QNKk/hqdefault.jpg", musicDto.thumbnailUrl)
    }

    @Test
    fun jsoupTest2() {
        val url = "https://www.youtube.com/watch?v=sFoWYa6QNKk"
        val musicDto = RequestUtils.makeMusicFrom(url)

        assertEquals(123, musicDto.length)
        assertEquals("https://www.youtube.com/watch?v=sFoWYa6QNKk", musicDto.url)
        assertEquals("『ぎゅって』feat. 初音ミク", musicDto.title)
        assertEquals("MIMI", musicDto.artist)
        assertEquals("2022-04-23", musicDto.uploaded.toString())
        assertEquals("https://i.ytimg.com/vi/sFoWYa6QNKk/hqdefault.jpg", musicDto.thumbnailUrl)
    }
}
