use crate::{GildedRose, Item};

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
