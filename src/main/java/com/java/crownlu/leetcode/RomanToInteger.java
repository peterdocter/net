package com.java.crownlu.leetcode;

import com.java.crownlu.wnys.AESUtils;
import org.python.google.common.collect.Lists;

import java.util.Arrays;

/**
 * Created by crownlu on 17/3/5.
 */
public class RomanToInteger {
    public static int romanToInt(String s) {
        MatchRomanNum mrn = new MatchRomanNum(s);
        return mrn.roman2int();
    }

    static class MatchRomanNum {
        String  s;
        int     i   = 0;
        int     sum = 0;

        MatchRomanNum(String s) {
            this.s = s;
        }

        int roman2int() {
            while (i < s.length()) {
                char c = s.charAt(i);
                switch (c) {
                    case 'M': m2int(); break;
                    case 'C': c2int(); break;
                    case 'D': d2int(); break;
                    case 'X': x2int(); break;
                    case 'L': l2int(); break;
                    case 'I': i2int(); break;
                    case 'V': v2int(); break;
                }
            }
            return sum;
        }

        void v2int() {
            String sub = s.substring(i);
            if (sub.startsWith("VIII")) {
                i = i + 4;
                sum = sum + 8;
            } else if (sub.startsWith("VII")) {
                i = i + 3;
                sum = sum + 7;
            }  else if (sub.startsWith("VI")) {
                i = i + 2;
                sum = sum + 6;
            } else {
                i++;
                sum = sum + 5;
            }
        }

        void i2int() {
            String sub = s.substring(i);
            if (sub.startsWith("III")) {
                i = i + 3;
                sum = sum + 3;
            } else if (sub.startsWith("IV")) {
                i = i + 2;
                sum = sum + 4;
            }  else if (sub.startsWith("II")) {
                i = i + 2;
                sum = sum + 2;
            } else if (sub.startsWith("IX")) {
                i = i + 2;
                sum = sum + 9;
            } else {
                i++;
                sum = sum + 1;
            }
        }

        void l2int() {
            String sub = s.substring(i);
            if (sub.startsWith("LXXX")) {
                i = i + 4;
                sum = sum + 80;
            } else if (sub.startsWith("LXX")) {
                i = i + 3;
                sum = sum + 70;
            }  else if (sub.startsWith("LX")) {
                i = i + 2;
                sum = sum + 60;
            } else {
                i++;
                sum = sum + 50;
            }
        }

        void x2int() {
            String sub = s.substring(i);
            if (sub.startsWith("XXX")) {
                i = i + 3;
                sum = sum + 30;
            } else if (sub.startsWith("XX")) {
                i = i + 2;
                sum = sum + 20;
            }  else if (sub.startsWith("XL")) {
                i = i + 2;
                sum = sum + 40;
            } else if (sub.startsWith("XC")) {
                i = i + 2;
                sum = sum + 90;
            } else {
                i++;
                sum = sum + 10;
            }
        }

        void d2int() {
            String sub = s.substring(i);
            if (sub.startsWith("DCCC")) {
                i = i + 4;
                sum = sum + 800;
            } else if (sub.startsWith("DCC")) {
                i = i + 3;
                sum = sum + 700;
            }  else if (sub.startsWith("DC")) {
                i = i + 2;
                sum = sum + 600;
            } else {
                i++;
                sum = sum + 500;
            }
        }

        void c2int() {
            String sub = s.substring(i);
            if (sub.startsWith("CCC")) {
                i = i + 3;
                sum = sum + 300;
            } else if (sub.startsWith("CC")) {
                i = i + 2;
                sum = sum + 200;
            }  else if (sub.startsWith("CD")) {
                i = i + 2;
                sum = sum + 400;
            } else if (sub.startsWith("CM")) {
                i = i + 2;
                sum = sum + 900;
            } else {
                i++;
                sum = sum + 100;
            }
        }

        void m2int() {
            String sub = s.substring(i);
            if (sub.startsWith("MMM")) {
                i = i + 3;
                sum = sum + 3000;
            } else if (sub.startsWith("MM")) {
                i = i + 2;
                sum = sum + 2000;
            } else {
                i++;
                sum = sum + 1000;
            }
        }
    }

    public static void main(String [] args) {
        AESUtils.p(romanToInt("MDCLXVI"));
        Lists.newArrayList(1,2,3);
        Arrays.asList();
    }
}
