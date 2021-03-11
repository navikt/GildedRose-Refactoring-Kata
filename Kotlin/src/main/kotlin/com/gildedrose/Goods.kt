package com.gildedrose

interface Goods {
    fun age()
    fun updateQuality()
    fun updateSellIn()

    companion object {
        fun Item.asGoods(): Goods = NormalGoods(this)
    }
}

class NormalGoods internal constructor(private val item: Item) : Goods {
    override fun age() {
        updateSellIn()
        updateQuality()
    }

    override fun updateQuality() {
        if (item.name != "Aged Brie" && item.name != "Backstage passes to a TAFKAL80ETC concert") {
            if (item.quality > 0) {
                if (item.name != "Sulfuras, Hand of Ragnaros") {
                    item.quality--
                }
            }
        } else {
            if (item.quality < 50) {
                item.quality++

                if (item.name == "Backstage passes to a TAFKAL80ETC concert") {
                    if (item.sellIn < 10) {
                        if (item.quality < 50) {
                            item.quality++
                        }
                    }

                    if (item.sellIn < 5) {
                        if (item.quality < 50) {
                            item.quality++
                        }
                    }
                }
            }
        }

        if (item.sellIn < 0) {
            if (item.name == "Aged Brie") {
                if (item.quality < 50) {
                    item.quality++
                }
            } else {
                if (item.name == "Backstage passes to a TAFKAL80ETC concert") {
                    item.quality = 0
                } else {
                    if (item.quality > 0) {
                        if (item.name != "Sulfuras, Hand of Ragnaros") {
                            item.quality--
                        }
                    }
                }
            }
        }
    }

    override fun updateSellIn() {
        if (item.name != "Sulfuras, Hand of Ragnaros") {
            item.sellIn--
        }
    }
}
