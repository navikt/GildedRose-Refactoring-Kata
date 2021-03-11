package com.gildedrose

import com.gildedrose.Goods.Companion.asGoods
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class GoodsTest {

    @Test
    fun `All items have a SellIn value which denotes the number of days we have to sell the item`() {
        val item = Item("Sword", sellIn = 6, quality = 3)
        item.asGoods().age()
        assertEquals(5, item.sellIn)
    }

}
