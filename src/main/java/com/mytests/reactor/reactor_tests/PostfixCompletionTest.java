package com.mytests.reactor.reactor_tests;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.tools.agent.ReactorDebugAgent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * *******************************
 * Created by Irina.Petrovskaya on 11/14/2019.
 * Project: reactor_tests
 * *******************************
 */
public class PostfixCompletionTest {
    public static void main(String[] args) {
        ReactorDebugAgent.init();
        POJO1 p1 = new POJO1("");
        Mono<String> m = Mono.fromSupplier(p1::method3);
        m.subscribeOn(Schedulers.parallel()).block();

        AtomicInteger num = new AtomicInteger();
        Mono<Integer> integerMono = Mono.fromSupplier(num::incrementAndGet);
    }


}
