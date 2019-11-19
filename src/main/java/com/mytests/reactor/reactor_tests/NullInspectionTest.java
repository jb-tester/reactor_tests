package com.mytests.reactor.reactor_tests;

import reactor.core.publisher.Flux;

import java.util.Random;
import java.util.function.Supplier;

/**
 * *******************************
 * Created by Irina.Petrovskaya on 11/19/2019.
 * Project: reactor_tests
 * *******************************
 */
public class NullInspectionTest {
    public static void main(String[] args) {
        a1();
        a2();
    }
    static void a1() {

         Flux.just(10)
                .map(i -> {
                    System.out.println(i);

                    Supplier<String> sup = () -> {
                        return null;
                    };
                    System.out.println(sup.get());
                    return sup;
                }).subscribe();

    }

    static class B {
        static final String lol = null;
    }

    static void a2() {
        reactor.core.publisher.Flux.just(10)
                .map(i -> {
                    System.out.println(i);
                    return B.lol;
                }).subscribe();
    }

    static void a3() {
        java.util.Random rnd = new java.util.Random();

        reactor.core.publisher.Flux.just(10)
                .map(i -> {
                    System.out.println(i);
              return getLol(rnd);
                });
    }

    private static String getLol(Random rnd) {
        return rnd.nextInt(2) == 0 ? null : "10";
    }
}
