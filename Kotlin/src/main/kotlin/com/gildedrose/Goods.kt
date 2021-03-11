package com.gildedrose

open class Goods internal constructor(private val item: Item) {
    fun age() {
        updateSellIn()
        updateQuality()
    }

    protected open fun updateQuality() {
        degradeQuality()
        if (item.sellIn < 0) {
            degradeQuality()
        }
    }

    protected fun increaseQuality() {
        if (item.quality < 50) {
            item.quality++
        }
    }

    private fun degradeQuality() {
        if (item.quality > 0) {
            item.quality--
        }
    }

    protected open fun updateSellIn() {
        item.sellIn--
    }

    companion object {
        fun Item.asGoods(): Goods = when (this.name) {
            "Sulfuras, Hand of Ragnaros" -> SulfurasGoods(this)
            "Aged Brie" -> AgedBrie(this)
            "Backstage passes to a TAFKAL80ETC concert" -> BackstagePasses(this)
            else -> Goods(this)
        }
    }
}

class BackstagePasses(private val item: Item) : Goods(item) {
    override fun updateQuality() {
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

    override fun updateQuality() {
    }
}
