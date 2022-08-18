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
}