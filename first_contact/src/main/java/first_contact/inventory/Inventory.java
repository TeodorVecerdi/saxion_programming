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
        InventoryChecks.put("GotEmptyCup", false);
        InventoryChecks.put("GotWaterCup", false);
        InventoryChecks.put("CoffeeMachineHasWater", false);
        InventoryChecks.put("CoffeeMachineHasCoffee", false);
        InventoryChecks.put("GotCoffee", false);
    }

    public void AddItem (Item item) {
        Items.add(item);
    }
    public void RemoveItem(Item item) {
        Items.remove(item);
    }
}

