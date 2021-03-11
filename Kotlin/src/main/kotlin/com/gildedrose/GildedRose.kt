package com.gildedrose

import com.gildedrose.Goods.Companion.asGoods

class GildedRose(var items: Array<Item>) {

    fun updateQuality() = items.map { it.asGoods() }.forEach(Goods::age)

}

