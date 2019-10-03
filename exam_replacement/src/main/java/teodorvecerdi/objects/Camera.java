package teodorvecerdi.objects;

import processing.core.PApplet;
import processing.core.PVector;
import teodorvecerdi.MainApp;
import teodorvecerdi.Transform;
import teodorvecerdi.misc.Input;
import teodorvecerdi.misc.RenderLayer;

public class Camera extends GameObject {

    public Transform Target;
    public float Zoom = 1f;
    public float MinZoom = 0.5f;
    public float MaxZoom = 2.5f;
    public float ZoomSpeed = 0.05f;

    public Camera(Object target) {
        super();
        renderLayer = RenderLayer.World;
        if (target instanceof Transform) Target = (Transform) target;
        else if (target instanceof GameObject) this.Target = ((GameObject) target).Transform;
        else throw new IllegalArgumentException("`target` should be of type GameObject or Transform");
    }

    @Override
    public void update() {
        float scroll = Input.GetScroll();
        Zoom += scroll * ZoomSpeed;
        Zoom = PApplet.constrain(Zoom, MinZoom, MaxZoom);
        Position.x = Target.Position.x * Zoom - MainApp.Instance.width / 2f + Target.Size.x / 2f * Zoom;
        Position.y = Target.Position.y * Zoom - MainApp.Instance.height / 2f + Target.Size.y / 2f * Zoom;
//        - MainApp.Instance.width/2f + Target.Size.x/2f
//        - MainApp.Instance.height/2f + Target.Size.y/2f
    }

    public PVector WorldToScreenPoint(PVector worldPosition) {
        PVector screenPosition = worldPosition.copy();
        screenPosition.x -= Target.Position.x - MainApp.Instance.width / 2f;
        screenPosition.y -= Target.Position.y - MainApp.Instance.height / 2f;
        return screenPosition;
    }

    @Override
    public void render() {

    }
}
