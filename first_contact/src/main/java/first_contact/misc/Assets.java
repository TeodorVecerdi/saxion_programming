package first_contact.misc;

import first_contact.Entry;
import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONArray;
import processing.data.JSONObject;
import processing.sound.SoundFile;

import java.util.HashMap;

public class Assets {
    public HashMap<String, PImage> Sprites;
    public HashMap<String, SoundFile> Sounds;
    public Assets() {
        Sprites = new HashMap<>();
        Sounds = new HashMap<>();
        var a = Entry.Instance;
        System.out.println("---- Loading sprites");
        JSONObject sprites = a.loadJSONObject("sprites.json");
        System.out.println("-------- Loading item sprites");
        var itemSprites = (JSONArray)sprites.get("itemSprites");
        for(int i = 0; i < itemSprites.size(); i++) {
            var item = (JSONObject)itemSprites.get(i);
            var image = a.loadImage(item.getString("sprite"));
            Sprites.put(item.getString("id"), image);
        }
        System.out.println("-------- Loading scene sprites");
        var sceneSprites = (JSONArray)sprites.get("sceneSprites");
        for(int i = 0; i < sceneSprites.size(); i++) {
            var sceneSprite = (JSONObject)sceneSprites.get(i);
            var image = a.loadImage(sceneSprite.getString("sprite"));
            Sprites.put(sceneSprite.getString("id"), image);
        }
        System.out.println("---- Loading sounds");
    }

    public PImage GetSprite(String id) {
        if(!Sprites.containsKey(id)) System.err.println(String.format("Sprite '%s' not found.", id));
        return Sprites.getOrDefault(id, Sprites.get("items/errorItem"));
    }


}
