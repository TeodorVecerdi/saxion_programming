package collision_testing.test3_rect_line;

public abstract class Loopable {
    public Loopable() {
        App.Instance.loopableList.add(this);
    }

    public abstract void render();

    public abstract void update();
}
