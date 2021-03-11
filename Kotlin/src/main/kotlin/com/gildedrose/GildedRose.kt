package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun behandleBackstage(item: Item) {
        when (item.sellIn) {
            in 10..Int.MAX_VALUE -> item.quality += 1
            in 5..9 -> item.quality += 2
            in 1..4 -> item.quality += 3
            else -> item.quality = 0
        }
    }

    fun behandleBrie(item: Item) {
        if (item.sellIn > 0) item.quality += 1
        else item.quality -= 2
    }

    fun behandleDefault(item: Item) {
        if (item.quality in 1..49) {
            if (item.sellIn > 0) item.quality -= 1
            else item.quality -= 2
        }
    }


    fun updateQuality() {
        val sulfuras = "Sulfuras, Hand of Ragnaros"
        val backstage = "Backstage passes to a TAFKAL80ETC concert"
        val brie = "Aged Brie"

        for (item in items) {

            if (item.name == sulfuras) continue
            item.sellIn = item.sellIn - 1
            when (item.name) {
                backstage -> behandleBackstage(item)
                brie -> behandleBrie(item)
                else -> behandleDefault(item)
            }
            if (item.quality > 50) item.quality = 50
            if (item.quality < 0) item.quality = 0
        }
    }

}

