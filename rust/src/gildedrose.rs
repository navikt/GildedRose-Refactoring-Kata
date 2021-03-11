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
		_ => update_common(item)
	    }
	}
    }
}

fn update_common(item: &mut Item) {
    if item.name != "Aged Brie" && item.name != "Backstage passes to a TAFKAL80ETC concert" {
	if item.quality > 0 {
	    item.quality -= 1;
	}
    } else {
	if item.quality < 50 {
	    item.quality += 1;

	    if item.name == "Backstage passes to a TAFKAL80ETC concert" {
		if item.sell_in < 11 && item.quality < 50 {
		    item.quality += 1;
		}

		if item.sell_in < 6 && item.quality < 50 {
		    item.quality += 1;
		}
	    }
	}
    }

    item.sell_in -= 1;

    if item.sell_in < 0 {
	if item.name != "Aged Brie" {
	    if item.name != "Backstage passes to a TAFKAL80ETC concert" {
		if item.quality > 0 {
		    item.quality -= 1;
		}
	    } else {
		item.quality = 0;
	    }
	} else {
	    if item.quality < 50 {
		item.quality += 1;
	    }
	}
    }
}
