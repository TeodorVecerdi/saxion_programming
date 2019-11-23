package first_contact.inventory;

import first_contact.Entry;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {
    public ArrayList<Item> Items;
    public int SelectedItem = -1;

    public HashMap<String, Boolean> InventoryChecks;

    public Inventory () {
        Items = new ArrayList<>();
        InventoryChecks = new HashMap<>();
        InventoryChecks.put("WaitingRoom/GotEmptyCup", false);
        InventoryChecks.put("WaitingRoom/GotWaterCup", false);
        InventoryChecks.put("WaitingRoom/CoffeeMachineHasWater", false);
        InventoryChecks.put("WaitingRoom/CoffeeMachineHasCoffee", false);
        InventoryChecks.put("WaitingRoom/GotCoffee", false);
        InventoryChecks.put("WaitingRoom/Unlocked", false);
        InventoryChecks.put("Bedroom/DrawerUnlocked", false);
        InventoryChecks.put("Bedroom/PuzzleDone", false);
        InventoryChecks.put("Bedroom/GotLockpick", false);
        InventoryChecks.put("Bedroom/GotKey", false);
    }

    public void AddItem (Item item) {
        Items.add(item);
    }
    public void RemoveItem(Item item) {
        Items.remove(item);
    }
}

