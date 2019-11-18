package com.mytests.reactor.reactor_tests;

import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

/**
 * *******************************
 * Created by Irina.Petrovskaya on 11/15/2019.
 * Project: reactor_tests
 * *******************************
 */
public class IntentionTest {

    public static void main(String[] args) {

        complex();
        simple();
        withMethodReference();
        withCast();
    }
    private static void simple() {
        Flux<POJO1> p = createPojos()
                .filter(pojo1 -> pojo1.getStr1().contains("7"))
                .map(pojo -> {
                    System.out.println("**********************************");
                    System.out.println(pojo.getStr1());
                    System.out.println("**********************************");
                    return pojo;
                }) ;
        p.subscribe();
    }
    private static void withMethodReference() {
        Flux<POJO1> p = createPojos()
                .filter(pojo1 -> pojo1.getStr1().contains("7"))
                .map(IntentionTest::m1) ;
        p.subscribe();
    }




    private static void withCast() {
        Flux<POJO1> p = createPojos()
                .filter(pojo1 -> pojo1.getStr1().contains("7"))
                .cast(POJO1.class)
                .map(pojo -> {
                    System.out.println("**********************************");
                    System.out.println(pojo.getStr1());
                    System.out.println("**********************************");
                    return pojo;
                }) ;
        p.subscribe();
    }
    private static void differentObjects() {
        Flux<POJO3> p = createPojos()
                .filter(pojo1 -> pojo1.getStr1().contains("s"))
                .map(pojo2 -> IntentionTest.apply(pojo2))
                .map(pojo3 -> {
                    System.out.println("**********************************");
                    System.out.println(pojo3.getStr());
                    System.out.println("**********************************");
                    return pojo3;
                })
                ;
        p.subscribe();
    }
    private static void complex() {
        Flux<POJO3> p = createPojos()
                .filter(pojo1 -> pojo1.getStr1().contains("s"))
                .filter(pojo1 -> Integer.valueOf(pojo1.method3()) > 3)
                .filter(pojo1 -> pojo1.getStr1().contains("7"))
                .cast(POJO1.class)
                .map(IntentionTest::apply)
                .map(pojo3 -> {
                    System.out.println("**********************************");
                    System.out.println(pojo3.getStr());
                    System.out.println("**********************************");
                    return pojo3;
                })
                .map(pojo3 -> {
                    System.out.println(pojo3);
                    return pojo3;
                });
        p.subscribe();
    }

    public static Flux<POJO2> createPojos(){

        List<POJO2> pojo1s = new ArrayList<>();
        pojo1s.add(new POJO2("s1"));
        pojo1s.add(new POJO2("s71"));
        pojo1s.add(new POJO2("s2"));
        pojo1s.add(new POJO2("s3"));
        pojo1s.add(new POJO2("s73"));
        pojo1s.add(new POJO2("s4"));
        pojo1s.add(new POJO2("s5"));
        pojo1s.add(new POJO2("s75"));
        pojo1s.add(new POJO2("s6"));
        pojo1s.add(new POJO2("s7"));
        pojo1s.add(new POJO2("s77"));
        pojo1s.add(new POJO2("s8"));
        pojo1s.add(new POJO2("s9"));
        pojo1s.add(new POJO2("s79"));
        return Flux.fromIterable(pojo1s);
    }

    private static POJO3 apply(POJO1 p1) {
        POJO3 m4 = p1.m4();
        return m4;
    }

    private static POJO1 m1(POJO2 pojo) {
        System.out.println("**********************************");
        System.out.println(pojo.getStr1());
        System.out.println("**********************************");
        return pojo;
    }
}
