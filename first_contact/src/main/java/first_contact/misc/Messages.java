package first_contact.misc;

import first_contact.Entry;

import java.security.SecureRandom;

public class Messages {
    public static final String[] NoHotspot = {
            "There is nothing to do here",
            "Hmm"
    };
    public static final String[] NoItem = {
            "I might need an item for this"
    };
    public static final String[] WrongItem = {
            "I think I have the wrong item for this",
            "I should try using something else"
    };
    public static final String[] WrongRoom = {
            "There is nothing more I can do in that room",
            "I've already been in that room"
    };

    public static final String [] NotNow = {
            "There is nothing I can do here now",
            "Maybe I should try later"
    };

    public static String GetRandom(String[] Message) {
        SecureRandom rand;
        if(Entry.Instance != null) rand = Entry.Instance.SecureRandom;
        else rand = new SecureRandom();

        return Message[rand.nextInt(Message.length)];
    }
}
