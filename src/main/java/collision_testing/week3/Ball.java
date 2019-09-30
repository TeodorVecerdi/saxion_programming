package collision_testing.week3;

import processing.core.PApplet;
import processing.core.PVector;

public class Ball extends GameObject {

    public float Speed;
    public PVector direction;

    public Ball(PApplet main) {
        this(main, 0, 0, 1, 1, 1, 1);
    }

    public Ball(PApplet main, float x, float y, float width, float height, float scale, float speed) {
        this.main = main;
        X = x;
        Y = y;
        Width = width;
        Height = height;
        Scale = scale;
        Speed = speed;
        direction = PVector.random2D(main);
    }

    @Override
    public void update() {
        X += Speed * direction.x;
        Y += Speed * direction.y;

        if(X < Width/2f) {
            direction.x *= -1;
            X = Width/2f;
        }
        if(X > main.width-Width/2) {
            direction.x *= -1;
            X = main.width-Width/2;
        }
        if(Y < Height/2f) {
            direction.y *= -1;
            Y = Height/2f;
        }
        if(Y > main.height-Height/2) {
            direction.y *= -1;
            Y = main.height-Height/2;
        }
    }

    @Override
    public void render() {
        main.pushMatrix();
        main.scale(Scale);
        main.translate(X, Y);

        main.fill(235, 64, 52);
        main.noStroke();
        main.ellipse(0, 0, Width, Height);
        main.popMatrix();
    }
}
