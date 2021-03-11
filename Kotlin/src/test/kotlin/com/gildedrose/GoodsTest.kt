package com.gildedrose

import com.gildedrose.Goods.Companion.asGoods
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

/*
	- All items have a SellIn value which denotes the number of days we have to sell the item
	- All items have a Quality value which denotes how valuable the item is
	- At the end of each day our system lowers both values for every item

Pretty simple, right? Well this is where it gets interesting:

	- Once the sell by date has passed, Quality degrades twice as fast
	- The Quality of an item is never negative
	- "Aged Brie" actually increases in Quality the older it gets
	- The Quality of an item is never more than 50
	- "Sulfuras", being a legendary item, never has to be sold or decreases in Quality
	- "Backstage passes", like aged brie, increases in Quality as its SellIn value approaches;
	Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less but
	Quality drops to 0 after the concert

 */

internal class GoodsTest {

    @Test
    fun `Each day lowers SellIn and Quality`() {
        val item = Item("Sword", sellIn = 6, quality = 3)
        item.asGoods().age()
        assertEquals(5, item.sellIn)
        assertEquals(2, item.quality)
    }

    @Test
    fun `Once the sell by date has passed, Quality degrades twice as fast`() {
        val item = Item("Sword", sellIn = 0, quality = 3)
        item.asGoods().age()
        assertEquals(1, item.quality)
    }


}
