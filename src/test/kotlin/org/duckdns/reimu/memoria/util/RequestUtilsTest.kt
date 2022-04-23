package org.duckdns.reimu.memoria.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class RequestUtilsTest {
    @Test
    fun jsoupTest() {
        val url = "https://www.youtube.com/watch?v=F1lu_LUnGpE"
        val musicDto = RequestUtils.makeMusicFrom(url)

        assertEquals(194, musicDto.length)
        assertEquals("【オリジナルMV】『 魔法少女とチョコレゐト / Magical Girl and Chocolate 』歌ってみた【ver.えむ子まる】", musicDto.title)
        assertEquals("えむ子まるchannel", musicDto.artist)
        assertEquals("2022-04-02", musicDto.uploaded.toString())
    }
}
