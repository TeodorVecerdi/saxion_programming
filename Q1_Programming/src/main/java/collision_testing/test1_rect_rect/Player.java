package collision_testing.test1_rect_rect;

import collision_testing.test1_rect_rect.collider.Collider2D;
import collision_testing.test1_rect_rect.collider.RectCollider2D;
import processing.core.PVector;

public class Player extends GameObject {

    public Collider2D Collider;
    //    Rectangle2D.Float boundingBox;
    public float Speed;
    private App main;

    public Player (App main, float speed, float x, float y) {
        this.main = main;
        Speed = speed;
        Position = new PVector(x, y);
        //        boundingBox = new Rectangle2D.Float(x, y, 50, 100);
        Collider = new RectCollider2D(main, this, x, y, 50, 100, 0, 0);
    }

    @Override
    public void update () {
        float horizontal = Input.GetAxisHorizontal();
        float vertical = Input.GetAxisVertical();
        PVector tempPos = Position.copy();
        Position.add(Speed * horizontal, Speed * vertical);
        Collider.update();

        for (Collider2D other : main.Colliders) {
            if (other == Collider) continue;
            if (Collider.CheckCollisionWith(other)) {
                PVector moveAmount = Collider.RespondToCollision(other);
                System.out.println(moveAmount);
                Position.add(moveAmount);
                Collider.update();
                break;
            }
        }
    }

    @Override
    public void render () {
        main.fill(0xffffb050);
        main.noStroke();
        main.rect(Position.x, Position.y, 50, 100);
        Collider.render();
    }
}
