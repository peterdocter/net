package com.java.crownlu.rxjava;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by crownlu on 17/1/18.
 */
public class HelloWorld {
    public static void hello(String... names) {
        Observable.from(names).subscribe(new Action1<String>() {

            @Override
            public void call(String s) {
                System.out.print(s + "  ");
            }
        });
    }

    public static void main(String [] args) {
        hello("hello", "world");
    }
}
