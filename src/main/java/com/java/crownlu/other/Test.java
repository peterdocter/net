package com.java.crownlu.other;

import org.jetbrains.annotations.NotNull;

/**
 * Created by crownlu on 17/1/18.
 */
public class Test {
    public static void test(@NotNull String s) {}

    public static void test1(String s) {}

    public static void test2(String s) {
        //assert need jvm param -ea
        assert s != null : "this is null";
        System.out.println(s);
    }

    public static void main(String [] args) {
        test1(null);
        test2(null);
        test(null);
    }
}
