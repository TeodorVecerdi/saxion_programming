package first_contact.inventory;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {
    public ArrayList<Item> Items;
    public int SelectedItem = -1;

    public HashMap<String, Boolean> InventoryChecks;

    public Inventory () {
        Items = new ArrayList<>();
        InventoryChecks = new HashMap<>();
        InventoryChecks.put("Room2/GotEmptyCup", false);
        InventoryChecks.put("Room2/GotWaterCup", false);
        InventoryChecks.put("Room2/CoffeeMachineHasWater", false);
        InventoryChecks.put("Room2/CoffeeMachineHasCoffee", false);
        InventoryChecks.put("Room2/GotCoffee", false);
        InventoryChecks.put("Room2/Unlocked", false);
    }

    public void AddItem (Item item) {
        Items.add(item);
    }
    public void RemoveItem(Item item) {
        Items.remove(item);
    }
}

