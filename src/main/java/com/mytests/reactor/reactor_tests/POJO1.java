package com.mytests.reactor.reactor_tests;

import reactor.core.publisher.Mono;

/**
 * *******************************
 * Created by Irina.Petrovskaya on 11/14/2019.
 * Project: reactor_tests
 * *******************************
 */
public class POJO1 {
    public POJO1(String str1) {
        this.str1 = str1;
    }

    String str1;
    public static String stMethod1(){
        return "";
    }

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public String method1(String arg1) {
        return "1" + " " + arg1;
    }

    public Mono<String> method2(String arg) {
        System.out.println(arg);

        return Mono.just(arg);
    }

    public String method3() {
        return this.getStr1().substring(1) ;
    }
    ;
    public static POJO3 m4(){
        return new POJO3("foo");
    }
}
