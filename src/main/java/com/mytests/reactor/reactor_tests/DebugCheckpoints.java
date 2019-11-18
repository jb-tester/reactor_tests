package com.mytests.reactor.reactor_tests;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalTime;

/**
 * *******************************
 * Created by Irina.Petrovskaya on 11/14/2019.
 * Project: reactor_tests
 * *******************************
 */
public class DebugCheckpoints {
    private static void checkpoint() {
        int seconds = LocalTime.now().getSecond();
        Mono<Integer> source;
        if (seconds % 2 == 0) {
            source = Flux.range(1, 10)
                    .elementAt(5)
                    .checkpoint("source range(1,10)");
        }
        else if (seconds % 3 == 0) {
            source = Flux.range(0, 4)
                    .elementAt(5)
                    .checkpoint("source range(0,4)");
        }
        else {
            source = Flux.just(1, 2, 3, 4)
                    .elementAt(5)
                    .checkpoint("source just(1,2,3,4)");
        }

        source.block(); //line 186
    }
}
