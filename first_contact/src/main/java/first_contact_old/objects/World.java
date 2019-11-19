package first_contact_old.objects;

import first_contact_old.misc.RenderLayer;

public class World extends GameObject {
    public int width;
    public int height;
    //          [x][y]
    public int[][] tiles;

    public World (int width, int height) {
        super();
        renderLayer = RenderLayer.NoCameraWorld;
    }

    @Override
    public void update () {

    }

    @Override
    public void render () {

    }
}
