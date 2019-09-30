package collision_testing.week3.misc;

import java.util.HashMap;

public class Input {
    private static HashMap<Integer, Boolean> _keys = new HashMap<>();
    private static HashMap<Integer, Boolean> _keysCheck = new HashMap<>();
    private static HashMap<Integer, Boolean> _buttons = new HashMap<>();
    private static HashMap<Integer, Boolean> _buttonsCheck = new HashMap<>();
    private static int _scroll = 0;
    private static boolean _isScrolled = false;

    public static void Refresh () {
        _isScrolled = false;
        _scroll = 0;
    }

    public static void PressKey (int key) {
        _keys.put(key, true);
        _keysCheck.put(key, false);
    }

    public static void ReleaseKey (int key) {
        _keys.put(key, false);
    }

    public static boolean IsKeyDown (int key) {
        return _keys.containsKey(key) && _keys.get(key);
    }

    public static boolean GetKeyDown (int key) {
        if (_keys.containsKey(key) && _keys.get(key)) {
            if (!_keysCheck.get(key)) {
                _keysCheck.put(key, true);
                return true;
            }
            return false;
        }
        return false;
    }

    public static void Scroll (int amount) {
        _scroll = -amount;
        _isScrolled = true;
    }

    public static int GetScroll () {
        return _scroll;
    }

    public static boolean IsScrolled () {
        return _isScrolled;
    }

    public static void PressButton (int key) {
        _buttons.put(key, true);
        _buttonsCheck.put(key, false);
    }

    public static void ReleaseButton (int key) {
        _buttons.put(key, false);
    }

    public static boolean IsButtonDown (int key) {
        return _buttons.containsKey(key) && _buttons.get(key);
    }

    public static boolean GetButtonDown (int key) {
        if (_buttons.containsKey(key) && _buttons.get(key)) {
            if (!_buttonsCheck.get(key)) {
                _buttonsCheck.put(key, true);
                return true;
            }
            return false;
        }
        return false;
    }
}
