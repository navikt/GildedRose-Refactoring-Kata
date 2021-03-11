package com.gildedrose

import com.gildedrose.Goods.Companion.asGoods

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        items.forEach { item ->
            helperItem(item)
        }
    }

    private fun helperItem(item: Item) {
        item.asGoods().helperItem()
    }

}

