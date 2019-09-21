package saxion_programming.week3;

import processing.core.PApplet;
import saxion_programming.week3.misc.Input;

import java.awt.event.KeyEvent;

public class Robot extends GameObject {
    public float Speed;

    private float ScrollScale = 0.1f;
    private BoxCollider Collider;
    private float idleAnimationSpeed = 0.8f;
    private float moveAnimationSpeed = 2.5f;
    private float desiredAnimationSpeed = idleAnimationSpeed;
    private float animationSpeed = idleAnimationSpeed;


    public Robot(PApplet main) {
        this(main, 0f, 0f, 150f, 200f, 1f, 1f);
    }

    public Robot(PApplet main, float x, float y, float width, float height, float scale, float speed) {
        this.main = main;
        X = x;
        Y = y;
        Width = width;
        Height = height;
        Scale = scale;
        Speed = speed;
        Collider = new BoxCollider(main, 50, 95, width, height);
    }

    public void update() {
        //Animation
        boolean isMoving = Input.IsKeyDown(KeyEvent.VK_W) || Input.IsKeyDown(KeyEvent.VK_A) || Input.IsKeyDown(KeyEvent.VK_S) || Input.IsKeyDown(KeyEvent.VK_D);
        if (isMoving) desiredAnimationSpeed = moveAnimationSpeed;
        else desiredAnimationSpeed = idleAnimationSpeed;
        animationSpeed = PApplet.lerp(animationSpeed, desiredAnimationSpeed, 0.1f);

        //Input
        if (Input.IsKeyDown(KeyEvent.VK_W)) Y -= Speed;
        if (Input.IsKeyDown(KeyEvent.VK_A)) X -= Speed;
        if (Input.IsKeyDown(KeyEvent.VK_S)) Y += Speed;
        if (Input.IsKeyDown(KeyEvent.VK_D)) X += Speed;
        if (Input.IsScrolled()) {
            System.out.println(Input.GetScroll());
            Scale += Input.GetScroll() * ScrollScale;
        }

        if (X < -Width * Scale) X = main.width;
        if (X > main.width) X = -Width * Scale;
        if (Y < -Height * Scale) Y = main.height;
        if (Y > main.height) Y = -Height * Scale;
    }

    public void render() {
        //<editor-fold desc="General">
        main.pushMatrix();
        main.translate(X, Y);
        main.scale(Scale);


        //<editor-fold desc="Head">
        //<editor-fold desc="Head/Main">
        main.fill(0xc5);
        main.stroke(0xaa);
        main.strokeWeight(1);
        main.rect(-10, 0, 120, 100, 20);
        //</editor-fold>
        //<editor-fold desc="Head/Ears">
        main.fill(134, 200, 240);
        main.noStroke();
        main.rect(-20, 40, 10, 20, 4);
        main.rect(110, 40, 10, 20, 4);
        //</editor-fold>
        //<editor-fold desc="Head/Eyes">
        main.fill(0);
        main.stroke(0x22);
        main.strokeWeight(1);
        main.ellipse(25, 45, 14, 13);
        main.ellipse(75, 45, 14, 13);
        //</editor-fold>
        //<editor-fold desc="Head/Mouth">
        main.fill(0);
        main.stroke(0);
        main.strokeWeight(1);
        main.rect(40, 55, 20, 4, 5);
        main.strokeWeight(4.5f);
        main.line(36, 54, 40, 57);
        main.line(64, 54, 60, 57);
        //</editor-fold>
        //<editor-fold desc="Head/Forehead">
        main.fill(0x44);
        main.noStroke();
        main.rect(17, 0, 66, 10);
        main.ellipse(50, 0, 68, 8);
        main.ellipse(23, 5, 20, 15);
        main.ellipse(78, 5, 20, 15);
        main.ellipse(50, 10, 68, 8);
        //</editor-fold>
        //</editor-fold>
        //<editor-fold desc="Body">
        //<editor-fold desc="Body/Main">
        main.fill(0xc5);
        main.stroke(0xaa);
        main.strokeWeight(1);
        main.rect(12, 95, 76, 76, 10);
        main.ellipse(50, 95, 50, 20);
        main.noStroke();
        main.rect(12, 84, 76, 10);
        //</editor-fold>
        //<editor-fold desc="Body/Power Button">
        main.pushMatrix();
        main.translate(35, 110);
        main.scale(1);
        main.noFill();
        drawPowerButton(0xaa, 0xaa, 0xaa, 8);
        drawPowerButton(134, 200, 240, 7);
        main.popMatrix();
        //</editor-fold>
        //<editor-fold desc="Body/Arms">
        main.fill(0xc5);
        main.stroke(0xaa);
        main.strokeWeight(1);
        main.pushMatrix();
        main.translate(12, 117.5f);
        rotate(10);
        main.pushMatrix();
        main.translate(-12, -117.5f);
        main.rect(2, 110, 10, 15, 10);
        main.pushMatrix();
        main.translate(2, 117.5f);
        rotate(10);
        main.pushMatrix();
        main.translate(-2, -117.5f);
        main.rect(-8, 110, 10, 15, 10);
        main.rect(-13, 108, 6, 10, 10);
        main.rect(-13, 118, 6, 10, 10);
        main.rect(-16, 108, 4, 8, 10);
        main.rect(-16, 120, 4, 8, 10);
        main.rect(-20, 108, 6, 3, 10);
        main.rect(-20, 125, 6, 3, 10);
        main.popMatrix();
        main.popMatrix();
        main.popMatrix();
        main.popMatrix();
        main.pushMatrix();
        main.translate(88, 117.5f);
        rotate(-10);
        main.pushMatrix();
        main.translate(-88, -117.5f);
        main.rect(88, 110, 10, 15, 10);
        main.pushMatrix();
        main.translate(98, 117.5f);
        rotate(-10);
        main.pushMatrix();
        main.translate(-98, -117.5f);
        main.rect(98, 110, 10, 15, 10);
        main.rect(107, 108, 6, 10, 10);
        main.rect(107, 118, 6, 10, 10);
        main.rect(112, 108, 4, 8, 10);
        main.rect(112, 120, 4, 8, 10);
        main.rect(114, 108, 6, 3, 10);
        main.rect(114, 125, 6, 3, 10);
        main.popMatrix();
        main.popMatrix();
        main.popMatrix();
        main.popMatrix();
        //</editor-fold>
        //<editor-fold desc="Body/Feet">
        main.fill(134, 200, 240);
        main.noStroke();
        main.rect(22, 171, 20, 20, 5);
        main.rect(58, 171, 20, 20, 5);
        //</editor-fold>
        //</editor-fold>

        Collider.render();
        main.popMatrix();
        //</editor-fold>
    }

    private void rotate(float degrees) {
        main.rotate(PApplet.radians((float) Math.sin(main.frameCount / 30f) * degrees) * animationSpeed);
    }

    private void drawPowerButton(float r, float g, float b, float sw) {
        main.stroke(r, g, b);
        main.strokeWeight(sw);
        main.beginShape();
        main.curveVertex(105, -150);
        main.curveVertex(5, 15);
        main.curveVertex(25, 15);
        main.curveVertex(-75, -150);
        main.endShape();
        main.line(15, 6, 15, 21);
    }

}
