package saxion_programming.week3;

import processing.core.PApplet;

public abstract class GameObject {
    public PApplet main;
    public float X, Y;
    public float Width, Height;
    public float Scale;

    public abstract void update();
    public abstract void render();
}
