package first_contact.objects;

import first_contact.MainApp;
import first_contact.RectCollider2D;
import first_contact.misc.CollisionLayer;
import first_contact.misc.RenderLayer;

public class Wall extends GameObject {
    RectCollider2D collider2D;

    public Wall(float x, float y, float width, float height) {
        super();
        renderLayer = RenderLayer.World;
        Position.x = x;
        Position.y = y;
        Size.x = width;
        Size.y = height;
        collider2D = new RectCollider2D(x, y, width, height, this, CollisionLayer.World);
        Tag = "Wall";
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        var a = MainApp.Instance;
        a.fill(0xffaa9999);
        a.stroke(0xffaa7777);
        a.strokeWeight(2f);
        a.rect(Position.x, Position.y, Size.x, Size.y);
    }
}
