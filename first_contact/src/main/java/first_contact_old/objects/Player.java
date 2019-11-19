package first_contact_old.objects;

import processing.core.PConstants;
import processing.core.PVector;
import first_contact_old.BulletManager;
import first_contact_old.MainApp;
import first_contact_old.RectCollider2D;
import first_contact_old.misc.CollisionLayer;
import first_contact_old.misc.Input;
import first_contact_old.misc.RenderLayer;

import java.awt.event.KeyEvent;

public class Player extends GameObject {
    public float Speed;
    public RectCollider2D collider2D;
    public float Rotation;
    private BulletManager bulletManager;
    private float flipX = 1f;

    public Player (float x, float y, float width, float height, float speed) {
        super();
        renderLayer = RenderLayer.World;
        Position.x = x;
        Position.y = y;
        Size.x = width;
        Size.y = height;
        Speed = speed;
        collider2D = new RectCollider2D(x, y, width, height, this, CollisionLayer.Player);
        collider2D.AddCollisionLayer(CollisionLayer.World);
        collider2D.ShouldRender = false;
        bulletManager = new BulletManager();
        Tag = "Player";
    }

    @Override
    public void update () {
        float dx = Speed * Input.GetAxisHorizontal();
        float dy = Speed * Input.GetAxisVertical();

        collider2D.Transform.Move(dx, dy);
        for (var collider : MainApp.Instance.colliders) {
            if (collider.equals(this.collider2D)) continue;
            if ((collider2D.collisionLayer & collider.collisionLayer) != 0 && collider2D.checkCollisionWith(collider)) {
                PVector response = collider2D.getCollisionResponse(collider);
                collider2D.Transform.Move(response.x, response.y);
                dx += response.x;
                dy += response.y;
            }
        }
        Position.x += dx;
        Position.y += dy;

        PVector screenCoords = MainApp.Instance.camera.WorldToScreenPoint(Position);
        Rotation = (float) (Math.atan2(screenCoords.y - MainApp.Instance.mouseY, screenCoords.x - MainApp.Instance.mouseX) - Math.PI / 2);
        if (Input.GetButtonDown(KeyEvent.VK_LEFT)) {
            System.out.println("Added bullet");
            bulletManager.fire(Position.x + Size.x / 2, Position.y + Size.x / 2, Rotation, 20f);
        }

        flipX = MainApp.Instance.mouseX > MainApp.Instance.width / 2f ? -1 : 1;
    }


    @Override
    public void render () {
        MainApp.Instance.pushMatrix();
        MainApp.Instance.translate(Position.x + Size.x / 2f, Position.y + Size.y / 2f);
        MainApp.Instance.scale(flipX, 1);
        //                MainApp.Instance.rotate(Rotation);
        MainApp.Instance.translate(-(Position.x + Size.x / 2f), -(Position.y + Size.y / 2f));
        MainApp.Instance.noStroke();
        MainApp.Instance.fill(0xffff3333);
        MainApp.Instance.rect(Position.x, Position.y, Size.x, Size.y);
        MainApp.Instance.fill(0xffdd0000);
        MainApp.Instance.ellipse(Position.x + Size.x / 2f - 20, Position.y + 20f, 10f, 10f);
        MainApp.Instance.ellipse(Position.x + Size.x / 2f + 10, Position.y + 20f, 10f, 10f);

        MainApp.Instance.popMatrix();
        MainApp.Instance.pushMatrix();
        MainApp.Instance.translate(Position.x + Size.x / 2f, Position.y + Size.y / 2f);
        float rot = Rotation - PConstants.PI / 2f + PConstants.PI;
        //        rot = PApplet.constrain(rot, -PConstants.PI/4, PConstants.PI);
        System.out.println(rot);
        //        if(flipX == -1) rot += 3*PConstants.PI/2f;
        MainApp.Instance.rotate(rot);
        MainApp.Instance.translate(-(Position.x + Size.x / 2f), -(Position.y + Size.y / 2f));
        MainApp.Instance.fill(0xffdd5555);
        MainApp.Instance.rect(Position.x - 25f, Position.y + Size.y / 2f - 30f, 15, 60);
        MainApp.Instance.popMatrix();
    }
}
