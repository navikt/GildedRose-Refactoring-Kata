use crate::{GildedRose, Item};

pub fn assert_common_item(name: &str) {
    let items = vec![Item::new(name, 5, 20)];
    let mut rose = GildedRose::new(items);

    rose.update_quality();
    assert_eq!(4, rose.items[0].sell_in);
    assert_eq!(19, rose.items[0].quality);

    (0..4).for_each(|_| rose.update_quality());
    assert_eq!(0, rose.items[0].sell_in);
    assert_eq!(15, rose.items[0].quality);

    rose.update_quality();
    assert_eq!(-1, rose.items[0].sell_in);
    assert_eq!(13, rose.items[0].quality);

    (0..99).for_each(|_| rose.update_quality());
    assert_eq!(-100, rose.items[0].sell_in);
    assert_eq!(0, rose.items[0].quality);
}

//- "Aged Brie" actually increases in Quality the older it gets
//- The Quality of an item is never more than 50
#[test]
pub fn aged_brie() {
    let items = vec![Item::new("Aged Brie", 2, 0)];
    let mut rose = GildedRose::new(items);

    rose.update_quality();
    assert_eq!(1, rose.items[0].sell_in);
    assert_eq!(1, rose.items[0].quality);

    rose.update_quality();
    assert_eq!(0, rose.items[0].sell_in);
    assert_eq!(2, rose.items[0].quality);

    rose.update_quality();
    assert_eq!(-1, rose.items[0].sell_in);
    assert_eq!(4, rose.items[0].quality);

    (0..5).for_each(|_| rose.update_quality());
    assert_eq!(-6, rose.items[0].sell_in);
    assert_eq!(14, rose.items[0].quality);
}

#[test]
pub fn sulfuras() {
    let items = vec![Item::new("Sulfuras, Hand of Ragnaros", 2, 80)];
    let mut rose = GildedRose::new(items);

    rose.update_quality();
    assert_eq!(2, rose.items[0].sell_in);
    assert_eq!(80, rose.items[0].quality);
}

#[test]
pub fn backstage_passes() {
    let items = vec![Item::new("Backstage passes to a TAFKAL80ETC concert", 15, 20)];
    let mut rose = GildedRose::new(items);

    rose.update_quality();
    assert_eq!(14, rose.items[0].sell_in);
    assert_eq!(21, rose.items[0].quality);

    (0..5).for_each(|_| rose.update_quality());
    assert_eq!(9, rose.items[0].sell_in);
    assert_eq!(27, rose.items[0].quality);

    (0..5).for_each(|_| rose.update_quality());
    assert_eq!(4, rose.items[0].sell_in);
    assert_eq!(38, rose.items[0].quality);

    (0..5).for_each(|_| rose.update_quality());
    assert_eq!(-1, rose.items[0].sell_in);
    assert_eq!(0, rose.items[0].quality);
}


#[test]
pub fn common_items() {
    assert_common_item("Elixir of the Mongoose");
    assert_common_item("+5 Dexterity Vest");
}
