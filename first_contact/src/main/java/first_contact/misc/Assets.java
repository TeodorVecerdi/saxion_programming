package first_contact.misc;

import first_contact.Entry;
import processing.core.PImage;
import processing.data.JSONArray;
import processing.data.JSONObject;
import processing.sound.SoundFile;

import java.util.HashMap;

public class Assets {
    public HashMap<String, PImage> Sprites;
    public HashMap<String, SoundFile> Sounds;

    public Assets () {
        Sprites = new HashMap<>();
        Sounds = new HashMap<>();
        var a = Entry.Instance;
        System.out.println("---- Loading sounds");
        JSONObject sounds = a.loadJSONObject("sounds.json");
        var soundArray = (JSONArray) sounds.get("sounds");
        for (int i = 0; i < soundArray.size(); i++) {
            var sound = (JSONObject) soundArray.get(i);
            var soundFile = new SoundFile(a, sound.getString("path"));
            Sounds.put(sound.getString("id"), soundFile);
        }
        System.out.println("---- Loading sprites");
        JSONObject sprites = a.loadJSONObject("sprites.json");
        System.out.println("-------- Loading item sprites");
        var itemSprites = (JSONArray) sprites.get("itemSprites");
        for (int i = 0; i < itemSprites.size(); i++) {
            var item = (JSONObject) itemSprites.get(i);
            var image = a.loadImage(item.getString("sprite"));
            Sprites.put(item.getString("id"), image);
        }
        System.out.println("-------- Loading scene sprites");
        var sceneSprites = (JSONArray) sprites.get("sceneSprites");
        for (int i = 0; i < sceneSprites.size(); i++) {
            var sceneSprite = (JSONObject) sceneSprites.get(i);
            var image = a.loadImage(sceneSprite.getString("sprite"));
            Sprites.put(sceneSprite.getString("id"), image);
        }

    }

    public PImage GetSprite (String id) {
        if (!Sprites.containsKey(id)) System.err.println(String.format("Sprite '%s' not found.", id));
        return Sprites.getOrDefault(id, Sprites.get("items/errorItem"));
    }

    public SoundFile GetSound (String id) {
        if (!Sounds.containsKey(id)) System.err.println(String.format("Sound '%s' not found.", id));
        return Sounds.getOrDefault(id, null);
    }

}
