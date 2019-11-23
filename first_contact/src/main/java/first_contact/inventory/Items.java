package first_contact.inventory;

import first_contact.Entry;
import processing.data.JSONArray;
import processing.data.JSONObject;

import java.util.HashMap;

public class Items {
    public HashMap<String, Item> Items;

    public Items () {
        Items = new HashMap<>();
        var items = (JSONArray) (Entry.Instance.loadJSONObject("items.json").get("items"));
        for (int i = 0; i < items.size(); i++) {
            var item = (JSONObject) items.get(i);
            Items.put(item.getString("id"), new Item(item.getString("id"), item.getString("name")));
        }
    }

    public Item GetItem(String id) {
        if(!Items.containsKey(id)) System.err.println(String.format("Item '%s' not found.", id));
        return Items.getOrDefault(id, Items.get("errorItem"));
    }
}
