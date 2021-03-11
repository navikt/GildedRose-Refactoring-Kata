package com.gildedrose

open class Goods internal constructor(private val item: Item) {
    fun age() {
        updateSellIn()
        updateQuality()
    }

    private fun updateQuality() {
        if (item.name == agedBrie) {
            increaseQuality()
            if (item.sellIn < 0) {
                increaseQuality()
            }

        } else if (item.name == backstagePasses) {
            increaseQuality()
            if (item.sellIn < 10) {
                increaseQuality()
            }

            if (item.sellIn < 5) {
                increaseQuality()
            }

            if (item.sellIn < 0) {
                item.quality = 0
            }

        } else {
            degradeQuality()
            if (item.sellIn < 0) {
                degradeQuality()
            }
        }
    }

    private fun increaseQuality() {
        if (item.quality < 50) {
            item.quality++
        }
    }

    private fun degradeQuality() {
        if (item.quality > 0) {
            if (item.name != sulfuras) {
                item.quality--
            }
        }
    }

    protected open fun updateSellIn() {
        item.sellIn--
    }

    companion object {
        val backstagePasses = "Backstage passes to a TAFKAL80ETC concert"
        val agedBrie = "Aged Brie"
        val sulfuras = "Sulfuras, Hand of Ragnaros"
        fun Item.asGoods(): Goods = if (this.name == sulfuras) SulfurasGoods(this) else Goods(this)
    }
}

class SulfurasGoods internal constructor(private val item: Item) : Goods(item) {

    override fun updateSellIn() {
    }
}
