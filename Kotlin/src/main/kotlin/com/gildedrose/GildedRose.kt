package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun behandleBackstage(item: Item) {
        when (item.sellIn ) {
         in 10 .. Int.MAX_VALUE -> incQuality(item.quality)
    }

    fun behandleBrie(item: Item) {
        if (item.quality < 50 ) {
            item.quality += 1
        }

    fun behandleDefault(item: Item) {
        if (item.quality > 50 ) {
            item.quality += 1
        }
    }

    fun incQuality(value: Int, change: Int = 1) = if (value < 50 ) {
            value + change
        } else value

    fun decQuality(value: Int, change: Int = 1) = if (value > 0 ) {
        value - change
    } else value

    fun updateQuality() {
        val sulfuras = "Sulfuras, Hand of Ragnaros"
        val backstage = "Backstage passes to a TAFKAL80ETC concert"
        val brie = "Aged Brie"

        for (item in items) {

            item.sellIn = item.sellIn - 1

            when (item.name) {
                backstage -> behandleBackstage(item)
                brie -> behandleBrie(item)
                sulfuras -> continue
                else -> behandleDefault(item)
            }




            if (item.name != brie && item.name != backstage) {
                if (item.quality > 0) {
                    item.quality = item.quality - 1
                }
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1

                    if (item.name == backstage) {
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
                if (item.name != brie) {
                    if (item.name != backstage) {
                        if (item.quality > 0) {
                            item.quality = item.quality - 1
                        }
                    } else {
                        item.quality = item.quality - item.quality
                    }
                } else {
                    if (item.quality < 50) {
                        item.quality = item.quality + 1
                    }
                }
            }
        }
    }

}

