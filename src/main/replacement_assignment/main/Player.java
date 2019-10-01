package main;

import main.misc.GameObject;
import main.misc.Input;
import processing.core.PVector;

import java.awt.event.KeyEvent;

public class Player extends GameObject {
    public float Speed;
    public RectCollider2D collider2D;
    public float Rotation;
    Bullet bullet;

    private BulletManager bulletManager;

    public Player(float x, float y, float width, float height, float speed) {
        super();
        Position.x = x;
        Position.y = y;
        Size.x = width;
        Size.y = height;
        Speed = speed;
        collider2D = new RectCollider2D(x, y, width, height, this, CollisionLayer.Player);
        collider2D.AddCollisionLayer(CollisionLayer.World);
        bulletManager = new BulletManager();
        Tag = "Player";
    }

    @Override
    public void update() {

        bulletManager.update();

        float dx = Speed * Input.GetAxisHorizontal();
        float dy = Speed * Input.GetAxisVertical();

        collider2D.Transform.Move(dx, dy);
        for (var collider : MainApp.Instance.colliders) {
            if (collider.equals(this.collider2D)) continue;
            if ((collider2D.collisionLayer & collider.collisionLayer) != 0 && collider2D.checkCollisionWith(collider)) {
                PVector response = collider2D.getCollisionResponse(collider);
                dx += response.x;
                dy += response.y;
            }
        }
        Position.x += dx;
        Position.y += dy;

        PVector screenCoords = MainApp.Instance.camera.WorldToScreenPoint(Position);
//        Rotation = (float) (Math.toRadians(PApplet.map((float) Math.toDegrees(Math.atan2(Position.y - MainApp.Instance.mouseY, Position.x - MainApp.Instance.mouseX)), -180, 180, 0, 360)) + Math.PI/2);
        Rotation = (float) (Math.atan2(screenCoords.y - MainApp.Instance.mouseY, screenCoords.x - MainApp.Instance.mouseX) - Math.PI / 2);
        if (Input.GetButtonDown(KeyEvent.VK_LEFT)) {
//            bullet = new Bullet(Position.x, Position.y, new PVector((float) Math.sin(Rotation), (float) Math.cos(Rotation)), 10f);
            System.out.println("Added bullet");
            bulletManager.fire(Position.x + Size.x / 2, Position.y + Size.x / 2, Rotation, 20f);
//            new Bullet(Position.x, Position.y, new PVector((float)Math.sin(Rotation), (float)Math.cos(Rotation)), 10f);
        }

    }


    @Override
    public void render() {
        MainApp.Instance.pushMatrix();
        MainApp.Instance.translate(Position.x + Size.x / 2f, Position.y + Size.y / 2f);
        MainApp.Instance.rotate(Rotation);
        MainApp.Instance.translate(-(Position.x + Size.x / 2f), -(Position.y + Size.y / 2f));
        MainApp.Instance.fill(0xffff3333);
        MainApp.Instance.noStroke();
        MainApp.Instance.rect(Position.x + Size.x / 2f - 10f, Position.y - 10f, 20f, 20f);
        MainApp.Instance.rect(Position.x, Position.y, Size.x, Size.y);
        MainApp.Instance.popMatrix();
        bulletManager.render();
    }
}
