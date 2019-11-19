package first_contact_old.misc;

import java.util.HashMap;

public class TileDictionary {
    public static TileDictionary Instance = new TileDictionary();
    public HashMap<String, Tile> idDictionary = new HashMap<>();

    private TileDictionary () {
        for (Tile tile : Tile.values()) {
            idDictionary.put(tile.Id, tile);
        }
    }
}
