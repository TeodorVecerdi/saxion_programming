package first_contact.objects;


public abstract class Scene extends GameObject {
    public final String SceneName;
    public static boolean HotspotClickedThisFrame;

    public Scene () {
        super();
        SceneName = String.format("Scene_[%s]", getClass().getSimpleName());
    }
}
