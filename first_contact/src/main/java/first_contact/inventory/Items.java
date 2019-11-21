package first_contact.inventory;

public class Items {
    public Item EmptyCup, WaterCup, CoffeeCup, Crowbar, Lockpick, BedroomDrawerKey;

    public Items () {
        EmptyCup = new Item("Empty Cup", "Items/emptyCup.png");
        WaterCup = new Item("Water Cup", "Items/waterCup.png");
        CoffeeCup = new Item("Coffee Cup", "Items/coffeeCup.png");
        Crowbar = new Item("Crowbar", "Items/crowbar.png");
        Lockpick = new Item("Lockpick", "Items/lockpick.png");
        BedroomDrawerKey = new Item("Bedroom Drawer Key", "Items/bedroomDrawerKey.png");
    }
}
