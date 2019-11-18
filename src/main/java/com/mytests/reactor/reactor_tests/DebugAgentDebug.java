package com.mytests.reactor.reactor_tests;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.tools.agent.ReactorDebugAgent;

import java.time.LocalTime;

/**
 * *******************************
 * Created by Irina.Petrovskaya on 11/14/2019.
 * Project: reactor_tests
 * *******************************
 */
public class DebugAgentDebug {
    private static void reactiveNoSubscribeOn() {
        ReactorDebugAgent.init();
        int seconds = (LocalTime.now().getSecond());
        foo(seconds);
    }

    static void foo(int seconds) {
        Mono<Integer> source;
        if (seconds % 2 == 0) {
            source = Flux.range(1, 10)
                    .elementAt(5);
        }
        else if (seconds % 3 == 0) {
            source = Flux.range(0, 4)
                    .elementAt(5);
        }
        else {
            source = Flux.just(1, 2, 3, 4)
                    .elementAt(5);
        }

        source.block(); //line 116
    }

    public static void main(String[] args) {
        reactiveNoSubscribeOn();
    }
}
