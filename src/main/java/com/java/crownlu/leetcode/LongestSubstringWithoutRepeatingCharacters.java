package com.java.crownlu.leetcode;

import com.java.crownlu.wnys.AESUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by crownlu on 17/3/1.
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public static int lengthOfLongestSubstring(String s) {
        int max = 0;
        if (s != null) {
            Map<Character, Integer> charSet = new HashMap();
            int j = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                Integer lastRepeat = charSet.get(c);
                if (lastRepeat != null && lastRepeat >= j) {
                    j = lastRepeat + 1;
                }

                int len = i - j + 1;
                if (max < len) {
                    max = len;
                }

                charSet.put(c, i);
            }
        }
        return max;
    }

    public static void main(String [] args) {
        AESUtils.p(lengthOfLongestSubstring("aaaacaa"));
    }
}
