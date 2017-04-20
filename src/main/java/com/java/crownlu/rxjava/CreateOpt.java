package com.java.crownlu.rxjava;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.subjects.AsyncSubject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by crownlu on 17/1/18.
 */
public class CreateOpt {
    public static Observable create(String... args) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed() && args != null) {
                        if (args.length == 2) {
                            System.out.println("this thread need timeout " + Thread.currentThread());
                            //Thread.sleep(1000L);
                        }

                        for(String s : args) {
                            System.out.println("--- " + s + "  " + Thread.currentThread());
                            //subscriber.onNext(s);
                        }
                        subscriber.onCompleted();
                    }
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    public static AsyncSubject createAsync(String... args) {
        return AsyncSubject.create();
    }

    public static void main(String [] args) {

        /**
         * 下面的代码说明Observable OnSubscribe 的(由create产生)操作是在subscribe调用的时候执行的
         * Observable.OnSubscribe的call操作实际上定义了调用图
         */
        System.out.println("+++");
        Observable observable = create("hello", "world", "x");
        System.out.println("=======");
        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String o) {
                System.out.println(o);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        }, new Action0() {

            @Override
            public void call() {
                System.out.println("hello world");
            }
        });
        System.out.println("1");

        /**
         *
         */
        AsyncSubject subject = AsyncSubject.create();
        System.out.println("\n\n\n\n---------------------------------");
        List<Observable<String>> observals = new ArrayList<Observable<String>>() {
            {
                add(create("hello"));
                add(create("world"));
                add(create("args"));
            }
        };

        System.out.println("---1");
        Observable<String> ob = Observable.mergeDelayError(observals);
        System.out.println("---2");
        ob.onErrorReturn(new Func1<Throwable, String>() {
            @Override
            public String call(Throwable t) {
                System.out.println("error" + t);
                t.printStackTrace();
                return null;
            }
        }).subscribe(subject);

        System.out.println("---3");
        Object s = subject.doOnError(new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        }).toBlocking().lastOrDefault("finish");

        System.out.println("---4");
        System.out.println(s);
    }
}
