package first_contact.scenes;

import first_contact.Entry;
import first_contact.misc.Globals;
import first_contact.misc.Utils;
import first_contact.objects.Scene;
import processing.core.PConstants;
import processing.core.PImage;
import processing.data.JSONArray;
import processing.data.JSONObject;
import processing.sound.SoundFile;

import java.util.concurrent.TimeUnit;

public class Loading extends Scene {

    public static boolean LoadingDone = false;
    public static boolean StartLoading = false;
    public PImage Background;
    public String CurrentLoading = "";
    public String CurrentLoadingSecond = "";
    public float PercentageDone = 0f;

    public Loading () {
        super();
        var a = Entry.Instance;
        Background = a.loadImage("loadingScreen.png");
        StartLoading = true;
    }

    @Override
    public void update (float deltaTime) {
        if (StartLoading) {
            Load();
            StartLoading = false;
        }
        if(ShouldFade && FadeInTimeLeft >= 0) {
            FadeInTimeLeft -= deltaTime;
        }
        if(ShouldFade && FadeInTimeLeft <= 0) {
            ShouldFade = false;
        }
        if (LoadingDone) {
            Entry.Instance.ActiveScene = "Prologue";
            LoadingDone = false;
        }
    }

    @Override
    public void render () {
        var a = Entry.Instance;
        a.pushMatrix();
        a.image(Background, 0, 0);
        a.noStroke();
        float fillAmount = PercentageDone * (Globals.WIDTH - 210);
        if (!CurrentLoading.equals("DONE")) {
            a.fill(0xff2c2c2c);
            a.rect(100, Globals.HEIGHT / 2 - 50, Globals.WIDTH - 200, 100);
            a.fill(0xffa7a7a7);
            a.rect(105, Globals.HEIGHT / 2 - 45, fillAmount, 90);
            a.fill(0xff);
        }
        a.textAlign(PConstants.CENTER, PConstants.CENTER);
        a.textSize(150);
        if (CurrentLoading.equals("DONE")) a.text(CurrentLoading, Globals.WIDTH / 2, Globals.HEIGHT / 2);
        else a.text(CurrentLoading, Globals.WIDTH / 2, Globals.HEIGHT / 2 - 150);
        a.textSize(40);
        a.text(CurrentLoadingSecond, Globals.WIDTH / 2, Globals.HEIGHT / 2 + 80);
        a.textAlign(PConstants.LEFT, PConstants.BASELINE);

        if (ShouldFade) {
            var fadeAmt = Utils.Map(FadeInTimeLeft, 0f, FadeInTime, 0f, 1f) * 0xff;
            a.fill(0x0, fadeAmt);
            a.rect(0, 0, Globals.WIDTH, Globals.HEIGHT);
        }

        if (Globals.SHOW_DEBUG) {
            a.fill(0, 0, 255);
            a.textSize(35);
            a.text(String.format("%s (%s)", Name, SceneName), 20, 30);
        }

        a.popMatrix();
    }

    public void Load () {
        new Thread(() -> {
            var a = Entry.Instance;
            CurrentLoading = "LOADING SOUNDS";
            CurrentLoadingSecond = "LOADING SOUNDS";
            JSONObject sounds = a.loadJSONObject("sounds.json");
            var soundArray = (JSONArray) sounds.get("sounds");
            float amtPerItem = 0.333f / soundArray.size();
            for (int i = 0; i < soundArray.size(); i++) {
                var sound = (JSONObject) soundArray.get(i);
                var soundFile = new SoundFile(a, sound.getString("path"));
                a.Assets.Sounds.put(sound.getString("id"), soundFile);
                PercentageDone += amtPerItem;
            }
            CurrentLoading = "LOADING SPRITES";
            CurrentLoadingSecond = "LOADING ITEM SPRITES";
            JSONObject sprites = a.loadJSONObject("sprites.json");
            var itemSprites = (JSONArray) sprites.get("itemSprites");
            amtPerItem = 0.333f / itemSprites.size();
            for (int i = 0; i < itemSprites.size(); i++) {
                var item = (JSONObject) itemSprites.get(i);
                var image = a.loadImage(item.getString("sprite"));
                a.Assets.Sprites.put(item.getString("id"), image);
                PercentageDone += amtPerItem;
            }
            CurrentLoadingSecond = "LOADING SCENE SPRITES";
            var sceneSprites = (JSONArray) sprites.get("sceneSprites");
            amtPerItem = 0.333f / sceneSprites.size();
            for (int i = 0; i < sceneSprites.size(); i++) {
                var sceneSprite = (JSONObject) sceneSprites.get(i);
                var image = a.loadImage(sceneSprite.getString("sprite"));
                a.Assets.Sprites.put(sceneSprite.getString("id"), image);
                PercentageDone += amtPerItem;
            }
            PercentageDone = 1f;
            CurrentLoading = "DONE";
            CurrentLoadingSecond = "";
            a.Scheduler.schedule(() -> {
                a.Assets.Loaded = true;
                LoadingDone = true;
            }, 750, TimeUnit.MILLISECONDS);
        }).start();
    }
}
