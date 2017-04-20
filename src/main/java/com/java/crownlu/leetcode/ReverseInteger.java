package com.java.crownlu.leetcode;

import com.java.crownlu.wnys.AESUtils;

/**
 * Created by crownlu on 17/3/5.
 */
public class ReverseInteger {

    public static int reverse(int x) {
        int sum = 0;
        for (; x != 0; x = x / 10) {
            int i = x % 10;
            if (i >= 0) {
                int M = Integer.MAX_VALUE / 10;
                if (M < sum || (M == sum && i > 7)) {
                    return 0;
                }
            } else {
                int N = Integer.MIN_VALUE / 10;
                if (N > sum || (N == sum && i < -8)) {
                    return 0;
                }
            }
            sum = i + sum * 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        AESUtils.p(Integer.MAX_VALUE - 0);
        AESUtils.p(Integer.MIN_VALUE - 0);
        AESUtils.p(reverse(1463847412));

        AESUtils.p("A".startsWith("AA"));
    }
}
