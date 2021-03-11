package com.gildedrose

open class Goods internal constructor(private val item: Item) {
    fun age() {
        updateSellIn()
        updateQuality()
    }

    private fun updateQuality() {
        if (item.name != "Aged Brie" && item.name != "Backstage passes to a TAFKAL80ETC concert") {
            degradeQuality()
        } else {
            increaseQuality()
            if (item.name == "Backstage passes to a TAFKAL80ETC concert") {
                if (item.sellIn < 10) {
                    increaseQuality()
                }

                if (item.sellIn < 5) {
                    increaseQuality()
                }
            }
        }

        if (item.sellIn < 0) {
            if (item.name == "Aged Brie") {
                increaseQuality()
            } else if (item.name == "Backstage passes to a TAFKAL80ETC concert") {
                item.quality = 0
            } else {
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
            if (item.name != "Sulfuras, Hand of Ragnaros") {
                item.quality--
            }
        }
    }

    protected open fun updateSellIn() {
        item.sellIn--
    }

    companion object {
        fun Item.asGoods(): Goods = if (this.name == "Sulfuras, Hand of Ragnaros") SulfurasGoods(this) else Goods(this)
    }
}

class SulfurasGoods internal constructor(private val item: Item) : Goods(item) {

    override fun updateSellIn() {
    }
}
