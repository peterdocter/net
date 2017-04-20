package com.java.crownlu.leetcode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by crownlu on 17/3/5.
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        } else if (strs.length == 1) {
            return strs[0];
        }

        List<Integer> lens = new LinkedList();

        for (int i = 0; i < strs.length; i++) {
            lens.add(strs[i].length());
        }
        int minLen = Collections.min(lens);
        if (minLen == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < minLen; i++) {
            char c = strs[0].charAt(i);

            int j = 1;
            for (; j < strs.length; j++) {
                if (c != strs[j].charAt(i)) {
                    break;
                }
            }

            if (j == strs.length) {
                sb.append(c);
            } else {
                break;
            }
        }

        return sb.toString();
    }
}
