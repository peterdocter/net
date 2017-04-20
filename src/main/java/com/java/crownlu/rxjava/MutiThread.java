package com.java.crownlu.rxjava;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.AsyncSubject;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by crownlu on 17/1/18.
 */
public class MutiThread {

    public static void test() {

        Observable<String> observable = CreateOpt.create("a", "b", "c", "d", "e", "f");
        Observable<String> observable2 = CreateOpt.create("a1", "b2", "c3", "d4", "e5", "f6");
        Observable<String> observable3 = CreateOpt.create("2222222221", "3333333333333");
        System.out.println("-----------");
        AsyncSubject<Observable<String>> subject = AsyncSubject.create();
        Observable<String> ob = observable.subscribeOn(Schedulers.newThread())
                .mergeWith(observable2).subscribeOn(Schedulers.newThread())
                .mergeWith(observable3).subscribeOn(Schedulers.newThread());
//                .timeout(20, TimeUnit.MILLISECONDS, Observable.empty()).subscribe(subject);

    }

    public static void main(String [] args) throws Exception {
        test();
    }
}
