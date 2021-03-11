package com.gildedrose

open class Goods internal constructor(private val item: Item) {
    fun age() {
        updateSellIn()
        updateQuality()
    }

    protected open fun updateQuality() {
        if (item.name == backstagePasses) {
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

    protected fun increaseQuality() {
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
        fun Item.asGoods(): Goods = if (this.name == sulfuras) SulfurasGoods(this) else if(this.name == agedBrie) AgedBrie(this) else Goods(this)
    }
}

class AgedBrie(private val item: Item) : Goods(item) {
    override fun updateQuality() {
        increaseQuality()
        if (item.sellIn < 0) {
            increaseQuality()
        }
    }
}

class SulfurasGoods internal constructor(private val item: Item) : Goods(item) {

    override fun updateSellIn() {
    }
}
