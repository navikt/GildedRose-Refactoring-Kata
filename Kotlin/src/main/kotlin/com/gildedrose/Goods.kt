package com.gildedrose

class Goods private constructor(private val item: Item) {
    fun age() {
        updateSellIn()
        updateQuality()
    }

    private fun updateQuality() {
        if (item.name != "Aged Brie" && item.name != "Backstage passes to a TAFKAL80ETC concert") {
            if (item.quality > 0) {
                if (item.name != "Sulfuras, Hand of Ragnaros") {
                    item.quality = item.quality - 1
                }
            }
        } else {
            if (item.quality < 50) {
                item.quality = item.quality + 1

                if (item.name == "Backstage passes to a TAFKAL80ETC concert") {
                    if (item.sellIn < 10) {
                        if (item.quality < 50) {
                            item.quality = item.quality + 1
                        }
                    }

                    if (item.sellIn < 5) {
                        if (item.quality < 50) {
                            item.quality = item.quality + 1
                        }
                    }
                }
            }
        }

        if (item.sellIn < 0) {
            if (item.name == "Aged Brie") {
                if (item.quality < 50) {
                    item.quality = item.quality + 1
                }
            } else {
                if (item.name == "Backstage passes to a TAFKAL80ETC concert") {
                    item.quality = 0
                } else {
                    if (item.quality > 0) {
                        if (item.name != "Sulfuras, Hand of Ragnaros") {
                            item.quality = item.quality - 1
                        }
                    }
                }
            }
        }
    }

    private fun updateSellIn() {
        if (item.name != "Sulfuras, Hand of Ragnaros") {
            item.sellIn = item.sellIn - 1
        }
    }

    companion object {
        fun Item.asGoods() = Goods(this)
    }
}
