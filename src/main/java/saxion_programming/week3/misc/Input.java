package saxion_programming.week3.misc;

import java.util.HashMap;

public class Input {
    private static HashMap<Integer, Boolean> _keys = new HashMap<>();
    private static HashMap<Integer, Boolean> _keysCheck = new HashMap<>();
    private static HashMap<Integer, Boolean> _buttons = new HashMap<>();
    private static HashMap<Integer, Boolean> _buttonsCheck = new HashMap<>();
    private static int _scroll = 0;
    private static boolean _isScrolled = false;

    public static void PressKey(int key) {
        _keys.put(key, true);
        _keysCheck.put(key, false);
    }

    public static void ReleaseKey(int key) {
        _keys.put(key, false);
    }

    public static boolean IsKeyDown(int key) {
        return _keys.containsKey(key) && _keys.get(key);
    }

    public static boolean GetKeyDown (int key) {
        if (_keys.containsKey(key) && _keys.get(key)) {
            if (!_keysCheck.get(key)) {
                _keysCheck.put(key, true);
                return true;
            } else return false;
        }
        return false;
    }

    public static void Scroll(int amount) {
        _scroll = -amount;
        _isScrolled = true;
    }

    public static int GetScroll(){
        return _scroll;
    }

    public static boolean IsScrolled() {
        return _isScrolled;
    }

    public static void Refresh() {
        _isScrolled = false;
        _scroll = 0;
    }
}
