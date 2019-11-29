package first_contact.misc;

import first_contact.Entry;
import first_contact.scenes.Loading;
import processing.core.PImage;
import processing.data.JSONArray;
import processing.data.JSONObject;
import processing.sound.SoundFile;

import java.util.HashMap;

public class Assets {
    public HashMap<String, PImage> Sprites;
    public HashMap<String, SoundFile> Sounds;
    public boolean Loaded = false;

    public Assets () {
        Sprites = new HashMap<>();
        Sounds = new HashMap<>();
    }

    public PImage GetSprite (String id) {
        if(!Loaded) {
            System.err.println("Loading not done!");
            return null;
        }
        if (!Sprites.containsKey(id)) System.err.println(String.format("Sprite '%s' not found.", id));
        return Sprites.getOrDefault(id, Sprites.get("items/errorItem"));
    }

    public SoundFile GetSound (String id) {
        if(!Loaded) {
            System.err.println("Loading not done!");
            return null;
        }
        if (!Sounds.containsKey(id)) System.err.println(String.format("Sound '%s' not found.", id));
        return Sounds.getOrDefault(id, null);
    }

}
