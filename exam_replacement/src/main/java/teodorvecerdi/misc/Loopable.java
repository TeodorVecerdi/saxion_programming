package teodorvecerdi.misc;

public interface Loopable {
    int renderLayer = RenderLayer.Default;

    void update();

    void render();
}
