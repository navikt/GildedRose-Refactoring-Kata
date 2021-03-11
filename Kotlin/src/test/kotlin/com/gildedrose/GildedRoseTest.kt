package com.gildedrose

import com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut
import org.approvaltests.Approvals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class GildedRoseTest {

    @Test
    fun `legger til 2 på konsert når det er mindre enn 10 dager og mer enn 5 dager til konserten`() {
        val items = arrayOf<Item>(Item("Backstage passes to a TAFKAL80ETC concert", 11, 21))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(10, app.items.first().sellIn)
        assertEquals(22, app.items.first().quality)
        app.updateQuality()
        assertEquals(9, app.items.first().sellIn)
        assertEquals(24, app.items.first().quality)
    }

    @Test
    fun `legger til 3 på konsert når det er mindre enn 5 dager`() {
        val items = arrayOf<Item>(Item("Backstage passes to a TAFKAL80ETC concert", 4, 20))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(3, app.items.first().sellIn)
        assertEquals(23, app.items.first().quality)
    }

    @Test
    fun `Gull master test`() {
        val actual = tapSystemOut { ->
            main(arrayOf())
        }
        Approvals.verify(actual)
        //assertEquals("",actual)
    }

}


