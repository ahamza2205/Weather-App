package com.example.data.cache

import android.content.SharedPreferences
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class CityInputCacheImplTest {

    private lateinit var cache: CityInputCacheImpl
    private lateinit var prefs: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    @Before
    fun setUp() {
        prefs = Mockito.mock(SharedPreferences::class.java)
        editor = Mockito.mock(SharedPreferences.Editor::class.java)

        Mockito.`when`(prefs.edit()).thenReturn(editor)

        Mockito.`when`(editor.putString(Mockito.anyString(), Mockito.anyString())).thenReturn(editor)

        cache = CityInputCacheImpl(prefs)
    }

    @Test
    fun `saveCity should save city name in SharedPreferences with correct key`() {
        val cityName = "Cairo"

        cache.saveCity(cityName)

        Mockito.verify(prefs).edit()
        Mockito.verify(editor).putString("last_city", cityName)
        Mockito.verify(editor).apply()
    }

    @Test
    fun `getLastCity should return city name when it exists in SharedPreferences`() {
        val cityName = "Cairo"
        Mockito.`when`(prefs.getString("last_city", null)).thenReturn(cityName)

        val result = cache.getLastCity()

        assertEquals(cityName, result)
        Mockito.verify(prefs).getString("last_city", null)
    }

    @Test
    fun `getLastCity should return null when no city is saved in SharedPreferences`() {
        Mockito.`when`(prefs.getString("last_city", null)).thenReturn(null)

        val result = cache.getLastCity()

        assertNull(result)
        Mockito.verify(prefs).getString("last_city", null)
    }
}