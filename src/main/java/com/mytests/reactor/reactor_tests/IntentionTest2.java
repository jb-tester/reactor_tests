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
public class IntentionTest2 {

    public static void main(String[] args) {

        filtersChain();

    }

    private static void filtersChain() {
        Flux<POJO2> p = createPojos()
                .filter(pojo1 -> pojo1.getStr1().startsWith("s"))
                .filter(pojo1 -> Integer.valueOf(pojo1.method3()) > 3)
                .filter(pojo1 -> pojo1.getStr1().contains("7"))

                .map(pojo1 -> {
                    System.out.println("**********************************");
                    System.out.println(pojo1.getStr1());
                    System.out.println("**********************************");
                    return pojo1;
                })
                ;
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


}
