package com.mytests.reactor.reactor_tests;

import reactor.blockhound.BlockHound;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * *******************************
 * Created by Irina.Petrovskaya on 11/19/2019.
 * Project: reactor_tests
 * *******************************
 */
public class BlockingIntentionTest {
   // @SuppressWarnings("NullableInLambdaInTransform")
    static void m() {
        Flux.just(1, 2)
                .map(i -> Mono.just(1).block())
                .delaySubscription(Duration.ofMillis(100))
                .subscribe() ;
    }
    static void subscribOnCheck2() {
        Flux<Integer> f = Flux.just(1, 2, 3)
                .subscribeOn(Schedulers.parallel());

        f.map(i -> Mono.just(1).block());
    }
    static void mixedScheduling(boolean b) {
        Flux<Integer> f = Flux.just(1, 2, 3)
                .map(i -> Mono.just(1).block());

        if (b) {
            f = f.subscribeOn(Schedulers.parallel());
        } else {
            f = f.subscribeOn(Schedulers.elastic());
        }

        if (b) {
            f = f.publishOn(Schedulers.immediate());
        } else {
            f = f.publishOn(Schedulers.elastic());
        }

        f = f.map(i -> Mono.just(i).block());
    }
    static void m2() {
        Mono.delay(Duration.ofMillis(100), Schedulers.parallel())
                .map(i -> Mono.just(1).block());
    }
    static void m3() {
        Mono.delay(Duration.ofMillis(100))
                .map(i -> Mono.just(1).block());
    }
    static void ifs(boolean b) {
        Flux<Integer> f = Flux.just(1, 2, 3);

        if (b) {
            f = f.publishOn(Schedulers.newParallel("p"));
        } else {
            f = f.publishOn(Schedulers.elastic());
        }

        Flux<Integer> ff = f
                .map(i -> i + 1)
                .map(i -> i + 2);


        ff.map(i -> {
            return Flux.just(1, 2, 3).toStream(3);
        });
    }
    static void m4() {
        Flux<Integer> f = Flux.just(1).publishOn(Schedulers.parallel());
        f = f.subscribeOn(Schedulers.single());
        f.map(i -> Mono.just(1).block());
    }
    static void foo() {
        Flux.range(0, Runtime.getRuntime().availableProcessors() * 2)
                .subscribeOn(Schedulers.parallel())
                .map(i -> {
                    CountDownLatch latch = new CountDownLatch(1);
                    //Mono.delay(Duration.ZERO).blockOptional();
                    Mono.delay(Duration.ofMillis(i * 100)).subscribe(it -> latch.countDown());

                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    return i;
                })
                .blockLast();
    }
    static void qqq()  {
        List<String> it = new ArrayList<>();
        it.add("1");
        it.add("2");
        it.add("3");

        String ff =
                Flux.fromIterable(it).map(s -> {
                    System.out.println(s);
                    return "last: "+ s;
                })
                        .subscribeOn(Schedulers.parallel())
                        .blockLast(Duration.ofSeconds(10));
        System.out.println(ff);
    }

    public static void main(String[] args) {
       // BlockHound.install();
        qqq();
        //boo();
        //foo();
        //m4();
        //m();
        //m2();
        //m3();
        //mixedScheduling(true);
        //ifs(true);
        //subscribOnCheck2();
    }

    static void boo(){
        Mono.delay(Duration.ofSeconds(1))
                .doOnNext(it -> {
                    try {
                        Thread.sleep(10);
                    }
                    catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                })
                .block();

    }
}
