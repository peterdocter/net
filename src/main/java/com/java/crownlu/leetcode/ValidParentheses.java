package com.java.crownlu.leetcode;

import com.java.crownlu.wnys.AESUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by crownlu on 17/3/5.
 */
public class ValidParentheses {
    static final String BEGIN  = "({[";
    static final Map<Character, Character> MAP
            = new HashMap<Character, Character>() {{
        put('[', ']');
        put('{', '}');
        put('(', ')');
    }};

    public static boolean isValid(String s) {
        if (s.isEmpty()) {
            return true;
        }

        if (s.length() % 2 == 1) {
            return false;
        }

        LinkedList<Character> vector = new LinkedList();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int ci = BEGIN.indexOf(c);

            if (ci > -1) {
                vector.push(c);
            } else {

                if (vector.size() < 1) {
                    return false;
                } else {
                    char begin = vector.pop();
                    AESUtils.p("v=" + vector + " b=" + begin + " c=" + c);
                    Character end = MAP.get(begin);
                    if (end == null || end != c) {
                        return false;
                    }
                }

            }
        }
        return vector.isEmpty();
    }

    public static void main(String [] args) {
        AESUtils.p(isValid("([)]"));
    }
}
