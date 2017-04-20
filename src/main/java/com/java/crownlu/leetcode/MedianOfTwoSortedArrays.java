package com.java.crownlu.leetcode;

import com.java.crownlu.wnys.AESUtils;

/**
 * Created by crownlu on 17/3/4.
 */
public class MedianOfTwoSortedArrays {
    static class MedianData {
        int[] a;
        int[] b;
        int len = 0;
        int alen = 0;
        int blen = 0;
        int mlen = 0;
        boolean is2 = false;
        int cmp = 0;
        int abCmp = 0;
        boolean aFirst = false;


        public MedianData(int[] nums1, int[] nums2) {
            this.a = nums1;
            this.b = nums2;

            alen = a.length;
            blen = b.length;
            len = alen + blen;

            mlen = len / 2;
            is2 = len % 2 == 0;
            cmp = a.length - b.length;

            if (alen == 0 || a[alen - 1] <= b[0]) {
                abCmp = -1;
            } else if (blen == 0 || b[blen - 1] <= a[0]) {
                abCmp = 1;
            }
        }

        double calMedian() {
            int [] base = b;
            int [] insert = a;
            if (alen >= mlen) {
                base = a;
                insert = b;
            }

            if (base[mlen] <= insert[0]) {
                if (is2) {
                    return cal2Median(base[mlen], base[mlen - 1]);
                } else {
                    return base[mlen];
                }
            } else {
                int rmi = base.length - mlen + 1;
                int maxInsert = insert[insert.length - 1];
                if (maxInsert <= base[rmi]) {
                    if (is2) {
                        return cal2Median(base[rmi], maxInsert < base[rmi - 1] ? base[rmi - 1] : maxInsert);
                    } else {
                        return base[rmi];
                    }
                } else {
                    int x = base[mlen];
                    if (is2) {
//todo
                        return x;
                    } else {
                        int j = mlen - 1;
                        for (int i = 0; i < insert.length; i++) {
                            int inv = insert[i];
                            if (x > inv) {
                                x = inv;
                                if (j >= 0 && x < base[j]) {
                                    x = base[j--];
                                } else {
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                        return x;
                    }
                }
            }
        }


        boolean canConnected() {
            return abCmp != 0;
        }

        double findConnectedMedian() {
            if (abCmp == -1) {
                return calConnected(a, b);
            } else {
                return calConnected(b, a);
            }
        }

        double calConnected(int[] a, int[] b) {
            if (cmp == 0) {
                return cal2Median(a[a.length - 1], b[0]);
            } else if (cmp > 0) {
                if (is2) {
                    return cal2Median(a[mlen - 1], a[mlen]);
                } else {
                    return a[mlen];
                }
            } else {
                int mi = mlen - a.length + 1;
                if (is2) {
                    return cal2Median(b[mi], b[mi - 1]);
                } else {
                    return b[mi];
                }
            }
        }

        double cal2Median(double x, double y) {
            return (x + y) / 2.0d;
        }
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > 0 || nums2.length > 0) {
            MedianData data = new MedianData(nums1, nums2);
            if (data.canConnected()) {
                return data.findConnectedMedian();
            } else {
                return data.calMedian();
            }
        }
        return 0;
    }

    public static void main(String [] args) {
        int[] nums2 = {1, 3};
        int[] nums1 = {4};

        AESUtils.p(findMedianSortedArrays(nums1, nums2));

        AESUtils.p(Integer.MAX_VALUE);
        AESUtils.p(Integer.MIN_VALUE);
        AESUtils.p(-3 % 10);
    }
}
