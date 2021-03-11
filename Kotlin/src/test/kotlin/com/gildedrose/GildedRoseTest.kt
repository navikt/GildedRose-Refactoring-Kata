package com.gildedrose

import com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut
import org.approvaltests.Approvals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class GildedRoseTest {

    @Disabled
    @Test
    fun foo() {
        val items = arrayOf<Item>(Item("foo", 0, 0))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals("fixme", app.items[0].name)
    }

    @Test
    fun integrationTest() {
        val actual = tapSystemOut {
            main(arrayOf())
        }
        Approvals.verify(actual)
    }

}


