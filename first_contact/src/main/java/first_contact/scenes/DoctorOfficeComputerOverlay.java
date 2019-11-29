package first_contact.scenes;

import first_contact.Entry;
import first_contact.misc.*;
import first_contact.objects.MouseHotspot;
import first_contact.objects.Scene;
import processing.core.PConstants;
import processing.core.PImage;

import java.awt.event.KeyEvent;

public class DoctorOfficeComputerOverlay extends Scene {

    public PImage Background;
    public PImage ComputerOverlay;
    public MouseHotspot BackHotspot;
    public String CorrectPassword = "1648";
    public String CurrentPassword = "";

    private float incorrectPasswordTimer = 0f;
    private float correctPasswordTimer = 0f;
    private boolean passwordCorrect = false;

    public DoctorOfficeComputerOverlay () {
        super();
        var a = Entry.Instance;

        BackHotspot = new MouseHotspot().AddCollisionTriangle(new Utils.Triangle(0, 941, 1920, 941, 0, 1080)).AddCollisionTriangle(new Utils.Triangle(1920, 941, 0, 1080, 1920, 1080)).AddAction(() -> {
            Scene.HotspotClickedThisFrame = true;
            a.ActiveScene = "DoctorOffice/Main";
        });
    }

    @Override public void Load () {
        var a = Entry.Instance;

        ComputerOverlay = a.Assets.GetSprite("scene/officeComputerOverlay");
        Background = ComputerOverlay;
    }

    @Override
    public void update (float deltaTime) {
        var a = Entry.Instance;
        if (FirstLoad) {
            Load();
            FirstLoad = false;
        }
        if(ShouldFade && FadeInTimeLeft >= 0) {
            FadeInTimeLeft -= deltaTime;
        }
        if(ShouldFade && FadeInTimeLeft <= 0) {
            ShouldFade = false;
        }
        BackHotspot.update(deltaTime);
        CheckPasswordKeyPresses();
        if (Input.GetKeyDown(KeyEvent.VK_BACK_SPACE)) {
            if (!CurrentPassword.isEmpty()) {
                CurrentPassword = CurrentPassword.substring(0, CurrentPassword.length() - 1);
            }
        }
        if (incorrectPasswordTimer > 0) incorrectPasswordTimer -= deltaTime;
        if (correctPasswordTimer > 0) correctPasswordTimer -= deltaTime;
        if (Input.GetKeyDown(KeyEvent.VK_ENTER) && !passwordCorrect) {
            if (!CurrentPassword.equals(CorrectPassword)) {
                incorrectPasswordTimer = 1.5f;
            } else {
                passwordCorrect = true;
                correctPasswordTimer = 2.5f;
                a.InventoryScene.PlayerInventory.InventoryChecks.put("DoctorOffice/Finished", true);
            }
        }

        if (Input.GetButtonDown(KeyEvent.VK_LEFT)) {
            if (!Scene.HotspotClickedThisFrame) {
                new FloatingText(Messages.GetRandom(Messages.NoHotspot), 1.5f);
            }
        }
    }

    @Override
    public void render () {
        var a = Entry.Instance;
        a.pushMatrix();
        a.image(Background, 0, 0);
        if (!passwordCorrect) {
            a.fill(0xff);
            a.textSize(40);
            a.textAlign(PConstants.CENTER, PConstants.CENTER);
            a.text("ENTER PASSWORD", 960, 300);
            a.textSize(200);
            a.textAlign(PConstants.LEFT, PConstants.CENTER);
            a.text(CurrentPassword, 683, 470);
            a.textSize(40);
            a.textAlign(PConstants.CENTER, PConstants.CENTER);
            a.text("PRESS ENTER TO UNLOCK COMPUTER", 960, 700);
            a.textAlign(PConstants.LEFT, PConstants.BASELINE);
            if (incorrectPasswordTimer > 0) {
                a.fill(0xffff0000);
                a.textSize(40);
                a.textAlign(PConstants.CENTER, PConstants.CENTER);
                a.text("INCORRECT PASSWORD", 960, 600);
            }
        } else if (correctPasswordTimer >= 0) {
            a.fill(0xff00ff00);
            a.textSize(200);
            a.textAlign(PConstants.LEFT, PConstants.CENTER);
            a.text(CorrectPassword, 683, 470);
        } else {
            a.fill(0xff);
            a.textAlign(PConstants.CENTER, PConstants.CENTER);
            a.textSize(70);
            a.text("ENTRANCE DOOR CODE:", 960, 300);
            a.textSize(200);
            a.text("9271", 960, 470);
        }
        a.textAlign(PConstants.LEFT, PConstants.BASELINE);

        if (ShouldFade) {
            var fadeAmt = Utils.Map(FadeInTimeLeft, 0f, FadeInTime, 0f, 1f) * 0xff;
            a.fill(0x0, fadeAmt);
            a.rect(0, 0, Globals.WIDTH, Globals.HEIGHT);
        }

        BackHotspot.render();
        //UI
        if (Globals.SHOW_DEBUG) {
            a.fill(0, 0, 255);
            a.textSize(35);
            a.text(String.format("%s (%s)", Name, SceneName), 20, 30);
        }

        a.popMatrix();
    }

    private void CheckPasswordKeyPresses () {
        if (Input.GetKeyDown(KeyEvent.VK_0) && CurrentPassword.length() < 4) CurrentPassword += "0";
        if (Input.GetKeyDown(KeyEvent.VK_1) && CurrentPassword.length() < 4) CurrentPassword += "1";
        if (Input.GetKeyDown(KeyEvent.VK_2) && CurrentPassword.length() < 4) CurrentPassword += "2";
        if (Input.GetKeyDown(KeyEvent.VK_3) && CurrentPassword.length() < 4) CurrentPassword += "3";
        if (Input.GetKeyDown(KeyEvent.VK_4) && CurrentPassword.length() < 4) CurrentPassword += "4";
        if (Input.GetKeyDown(KeyEvent.VK_5) && CurrentPassword.length() < 4) CurrentPassword += "5";
        if (Input.GetKeyDown(KeyEvent.VK_6) && CurrentPassword.length() < 4) CurrentPassword += "6";
        if (Input.GetKeyDown(KeyEvent.VK_7) && CurrentPassword.length() < 4) CurrentPassword += "7";
        if (Input.GetKeyDown(KeyEvent.VK_8) && CurrentPassword.length() < 4) CurrentPassword += "8";
        if (Input.GetKeyDown(KeyEvent.VK_9) && CurrentPassword.length() < 4) CurrentPassword += "9";
    }
}
