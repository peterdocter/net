package com.java.crownlu.leetcode;

/**
 * Created by crownlu on 17/3/5.
 */
public class PalindromeNumber {
    public static boolean isPalindrome(int x) {
        if (x < 10 && x > -1) {
            return true;
        }

        if (x > 0) {
            String s = x + "";
            int mid = s.length() / 2;
            for (int i = 0; i <= mid; i++) {
                char sc = s.charAt(i);
                char ec = s.charAt(s.length() - i - 1);
                if (sc != ec) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }
}
