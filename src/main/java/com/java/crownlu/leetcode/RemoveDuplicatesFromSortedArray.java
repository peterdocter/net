package com.java.crownlu.leetcode;


import java.util.concurrent.atomic.AtomicInteger;

import static com.java.crownlu.wnys.AESUtils.p;
/**
 * Created by crownlu on 17/3/6.
 */
public class RemoveDuplicatesFromSortedArray {

    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int i = 1;
        int j = i;
        int pre = nums[0];
        while (i < nums.length) {
            if (i != j) {
                nums[j] = nums[i];
            }

            if (nums[i] == pre) {
                while (i < nums.length && nums[i] == pre) {
                    i++;
                }
            } else {
                i++;
                j++;
            }
            pre = nums[i - 1];
        }
        return j;
    }

    public static void main(String [] args) {
        int [] nums = {1, 1, 2, 3, 4};
        p(removeDuplicates(nums));
        for (int i : nums) {
            p(" " + i);
        }

        AtomicInteger ato = new AtomicInteger(0);
        p(ato.getAndSet(1));
        p(ato.get());
    }
}
