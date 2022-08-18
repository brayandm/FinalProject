package com.example.finalproject

import com.example.finalproject.utils.Utils
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun utils_getId_isCorrect()
    {
        assertEquals(Utils().getID("Havana", "Cuba"), "Havana : Cuba")
        assertEquals(Utils().getID("New York City", "US"), "New York City : US")
        assertEquals(Utils().getID("Barcelona", "Spain"), "Barcelona : Spain")
    }

    @Test
    fun utils_isGoodPassword_isCorrect()
    {
        assertEquals(Utils().isGoodPassword("ab3a12cba1"), true)
        assertEquals(Utils().isGoodPassword("abc123456"), true)
        assertEquals(Utils().isGoodPassword("aaaaaaa1"), true)

        assertEquals(Utils().isGoodPassword("123456"), false)
        assertEquals(Utils().isGoodPassword("abc"), false)
        assertEquals(Utils().isGoodPassword("12345678"), false)
        assertEquals(Utils().isGoodPassword("0123456789"), false)
        assertEquals(Utils().isGoodPassword("abcdefgh"), false)
        assertEquals(Utils().isGoodPassword("abcdefghijklmnopqrstuvwxyz"), false)
    }

    @Test
    fun utils_containsDigit_isCorrect()
    {
        assertEquals(Utils().containsDigit("ab3a12cba1"), true)
        assertEquals(Utils().containsDigit("abc123456"), true)
        assertEquals(Utils().containsDigit("aaaaaaa1"), true)
        assertEquals(Utils().containsDigit("123456"), true)
        assertEquals(Utils().containsDigit("abc"), false)
        assertEquals(Utils().containsDigit("12345678"), true)
        assertEquals(Utils().containsDigit("0123456789"), true)
        assertEquals(Utils().containsDigit("abcdefgh"), false)
        assertEquals(Utils().containsDigit("abcdefghijklmnopqrstuvwxyz"), false)
    }

    @Test
    fun utils_containsLetter_isCorrect()
    {
        assertEquals(Utils().containsLetter("ab3a12cba1"), true)
        assertEquals(Utils().containsLetter("abc123456"), true)
        assertEquals(Utils().containsLetter("aaaaaaa1"), true)
        assertEquals(Utils().containsLetter("123456"), false)
        assertEquals(Utils().containsLetter("abc"), true)
        assertEquals(Utils().containsLetter("12345678"), false)
        assertEquals(Utils().containsLetter("0123456789"), false)
        assertEquals(Utils().containsLetter("abcdefgh"), true)
        assertEquals(Utils().containsLetter("abcdefghijklmnopqrstuvwxyz"), true)
    }
}