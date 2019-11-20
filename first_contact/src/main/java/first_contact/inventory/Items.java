package first_contact.inventory;

public class Items {
    public Item EmptyCup;
    public Item WaterCup;
    public Item CoffeeCup;
    public Items() {
        EmptyCup = new Item("Empty Cup", "emptyCup.png");
        WaterCup = new Item("Water Cup", "waterCup.png");
        CoffeeCup = new Item("Coffee Cup", "coffeeCup.png");
    }
}
