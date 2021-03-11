package com.gildedrose

import com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut
import org.approvaltests.Approvals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class GildedRoseTest {

    @Test
    fun foo() {
        val items = arrayOf<Item>(Item("foo", 0, 0))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals("foo", app.items[0].name)
    }

    @Test
    fun `quality can not be negative`() {
        val items = arrayOf(Item("foo", 0, 0))
        val app = GildedRose(items)
        app.updateQuality()
        assertTrue(app.items[0].quality >= 0)
    }

    @Test
    fun `quality degrades by one when sell by date is reduced by one`() {
        val items = arrayOf(Item("foo", 1, 4))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(3, app.items[0].quality)
    }

    @Test
    fun `quality degrades twice as fast when sell by date is passed`() {
        val items = arrayOf(Item("foo", 0, 4))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(2, app.items[0].quality)
    }

    @Test
    fun `brie increases in quality over time`() {
        val items = arrayOf(Item("Aged Brie", 0, 4))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(6, app.items[0].quality)
    }

    @Test
    fun `quality on brie can not increase above fifty`() {
        val items = arrayOf(Item("Aged Brie", 0, 50))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(50, app.items[0].quality)
    }

    @Test
    fun `quality and sellIn for Sulfuras does not change`() {
        val items = arrayOf(Item("Sulfuras, Hand of Ragnaros", 0, 80))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(80, app.items[0].quality)
        assertEquals( 0, app.items[0].sellIn)
    }

    @Test
    fun `sellin is less than 10 days quality increase 2 for backstage passes`() {
        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 8, 10))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(12, app.items[0].quality)
    }

    @Test
    fun `sellin is less than 5 days quality increase 3 for backstage passes`() {
        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 3, 10))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(13, app.items[0].quality)
    }

    @Test
    fun `sellin is more than 10 days quality increase 1 for backstage passes`() {
        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 11, 10))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(11, app.items[0].quality)
    }

    @Test
    fun `sellin is 0 quality is 0 for backstage passes`() {
        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 0, 10))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(0, app.items[0].quality)
    }

    @Test
    fun `conjured decrease quality twice`() {
        val items = arrayOf(Item("Conjured Mana Cake", 2, 10))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(8, app.items[0].quality)
    }

    @Test
    fun `golden master`(){
        val actual = tapSystemOut {
            main(arrayOf())
        }
        Approvals.verify(actual)
    }

}


