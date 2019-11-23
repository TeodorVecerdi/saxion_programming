package first_contact.inventory;

import first_contact.Entry;
import processing.core.PImage;

public class Item {
    public String ItemID;
    public String ItemName;

    public Item(String itemID, String itemName) {
        ItemID = itemID;
        ItemName = itemName;
    }

    public PImage ItemSprite() {
        return Entry.Instance.Assets.GetSprite("items/"+ItemID);
    }
}

