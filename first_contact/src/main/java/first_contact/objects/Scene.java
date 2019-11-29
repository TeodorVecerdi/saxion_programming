package first_contact.objects;


public abstract class Scene extends GameObject {
    public final String SceneName;
    public boolean FirstLoad = true;
    public boolean ShouldFade;
    public float FadeInTime = 0.5f;
    public float FadeInTimeLeft;
    public static boolean HotspotClickedThisFrame;

    public Scene () {
        super();
        SceneName = String.format("Scene_[%s]", getClass().getSimpleName());
    }

    public abstract void Load();
}
