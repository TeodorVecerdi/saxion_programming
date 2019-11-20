package first_contact.inventory;

import first_contact.Entry;
import processing.core.PImage;

public class Item {
    public String ItemName;
    public PImage ItemImage;

    public Item(String itemName, String itemImageName) {
        ItemName = itemName;
        ItemImage = Entry.Instance.loadImage(itemImageName);
    }
}

