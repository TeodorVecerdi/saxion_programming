package first_contact.misc;

import java.util.HashMap;

@SuppressWarnings("Duplicates")
public class Input {
    public static int MouseX;
    public static int MouseY;
    public static Utils.Point2f MousePosition = Utils.Point2f.From(0, 0);

    private static HashMap<Integer, Boolean> _keys = new HashMap<>();
    private static HashMap<Integer, Boolean> _keysCheck = new HashMap<>();
    private static HashMap<Integer, Boolean> _buttons = new HashMap<>();
    private static HashMap<Integer, Boolean> _buttonsCheck = new HashMap<>();
    private static int _scroll = 0;
    private static boolean _isScrolled = false;

    public static void Refresh () {
        _isScrolled = false;
        _scroll = 0;
        _buttonsCheck.replaceAll((s, v) -> true);
        _keysCheck.replaceAll((s, v) -> true);
    }

    public static void PressKey (int key) {
        _keys.put(key, true);
        _keysCheck.put(key, false);
    }

    public static void ReleaseKey (int key) {
        _keys.put(key, false);
    }

    public static void Scroll (int amount) {
        _scroll = -amount;
        _isScrolled = true;

    }

    public static void PressButton (int key) {
        _buttons.put(key, true);
        _buttonsCheck.put(key, false);
    }

    public static void ReleaseButton (int key) {
        _buttons.put(key, false);
    }

    public static boolean IsKeyDown (int key) {
        return _keys.containsKey(key) && _keys.get(key);
    }

    public static boolean GetKeyDown (int key) {
        if (_keys.containsKey(key) && _keys.get(key)) {
            return !_keysCheck.get(key);
        }
        return false;
    }

    public static float GetAxisHorizontal () {
        float axis = 0;
        if (IsKeyDown(java.awt.event.KeyEvent.VK_A) || IsKeyDown(java.awt.event.KeyEvent.VK_LEFT)) axis += -1;
        if (IsKeyDown(java.awt.event.KeyEvent.VK_D) || IsKeyDown(java.awt.event.KeyEvent.VK_RIGHT)) axis += 1;
        return axis;
    }

    public static float GetAxisVertical () {
        float axis = 0;
        if (IsKeyDown(java.awt.event.KeyEvent.VK_W) || IsKeyDown(java.awt.event.KeyEvent.VK_UP)) axis += -1;
        if (IsKeyDown(java.awt.event.KeyEvent.VK_S) || IsKeyDown(java.awt.event.KeyEvent.VK_DOWN)) axis += 1;
        return axis;
    }

    public static int GetScroll () {
        return _scroll;
    }

    public static boolean IsScrolled () {
        return _isScrolled;
    }

    public static boolean IsButtonDown (int key) {
        return _buttons.containsKey(key) && _buttons.get(key);
    }

    public static boolean GetButtonDown (int key) {
        if (_buttons.containsKey(key) && _buttons.get(key)) {
            return !_buttonsCheck.get(key);
        }
        return false;
    }

    public static boolean IsMouseInside (int x, int y, int w, int h) {
        return MouseX >= x && MouseX <= x + w && MouseY >= y && MouseY <= y + h;
    }
}
