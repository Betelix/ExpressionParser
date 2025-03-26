package expression.exceptions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Parenthesis {
    static Set<Character> op = new HashSet<>(Arrays.asList('(', '[', '{'));
    static Set<Character> cp = new HashSet<>(Arrays.asList(')', ']', '}'));

    public static boolean checkOpen(char ch) {
        return op.contains(ch);
    }

    public static boolean checkClose(char ch) {
        return cp.contains(ch);
    }
}
