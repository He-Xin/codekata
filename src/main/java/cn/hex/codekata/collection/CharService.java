package cn.hex.codekata.collection;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by hex.
 */
public class CharService {
    private CharProvider cp;
    private Set<Character> uniqChars = new LinkedHashSet<>();
    private Set<Character> occurredChars = new HashSet<>();


    public CharService(CharProvider cp) {
        this.cp = cp;
    }

    public char read() {
        char c = cp.getChar();
        if (!occurredChars.contains(c)) {
            occurredChars.add(c);
            uniqChars.add(c);
        } else if (uniqChars.contains(c)) {
            uniqChars.remove(c);
        }

        return c;
    }

    public Character getFirstUniq() {
        if (uniqChars.isEmpty()) {
            return null;
        }
        return uniqChars.iterator().next();
    }

}
