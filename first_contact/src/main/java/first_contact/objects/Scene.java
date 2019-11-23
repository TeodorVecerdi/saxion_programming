package first_contact.objects;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class Scene extends GameObject {
    public final String SceneName;
    public static boolean HotspotClickedThisFrame;

    public Scene () {
        super();
        SceneName = String.format("Scene_[%s]", getClass().getSimpleName());
    }
}
