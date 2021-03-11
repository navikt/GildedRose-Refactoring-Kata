use std::fmt::{self, Display};
pub struct Item {
    pub name: String,
    pub sell_in: i32,
    pub quality: i32,
}

impl Item {
    pub fn new(name: impl Into<String>, sell_in: i32, quality: i32) -> Item {
        Item {
            name: name.into(),
            sell_in,
            quality,
        }
    }
}

impl Display for Item {
    fn fmt(&self, f: &mut fmt::Formatter<'_>) -> fmt::Result {
        write!(f, "{}, {}, {}", self.name, self.sell_in, self.quality)
    }
}

pub struct GildedRose {
    pub items: Vec<Item>,
}

impl GildedRose {
    pub fn new(items: Vec<Item>) -> GildedRose {
        GildedRose { items }
    }

    pub fn update_quality(&mut self) {
        for item in &mut self.items {
            match item.name.as_str() {
                "Sulfuras, Hand of Ragnaros" => (),
                "Aged Brie" => update_brie(item),
                "Backstage passes to a TAFKAL80ETC concert" => update_backstage_pass(item),
                _ => update_common(item),
            }
        }
    }
}

fn update_brie(item: &mut Item) {
    item.sell_in -= 1;

    if item.quality < 50 {
        item.quality += 1;
        if item.sell_in < 0 {
            item.quality += 1;
        }
    }
}

fn update_backstage_pass(item: &mut Item) {
    item.sell_in -= 1;

    item.quality += 1;

    if item.sell_in < 11 {
        item.quality += 1;
    }
    if item.sell_in < 6 {
        item.quality += 1;
    }
    if item.sell_in < 0 {
        item.quality = 0;
    }

    item.quality = std::cmp::min(item.quality, 50);
}

fn update_common(item: &mut Item) {
    item.sell_in -= 1;

    if item.quality > 0 {
        item.quality -= 1;

        if item.sell_in < 0 {
            item.quality -= 1;
        }
    }
}
