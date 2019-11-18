package com.mytests.reactor.reactor_tests;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;

import java.time.LocalTime;

/**
 * *******************************
 * Created by Irina.Petrovskaya on 11/14/2019.
 * Project: reactor_tests
 * *******************************
 */
public class HookDebug {
    public static void main(String[] args) {
        hook();
    }
    private static void hook() {
        Hooks.onOperatorDebug();
        try {
            int seconds = LocalTime.now().getSecond();
            DebugAgentDebug.foo(seconds);
        }
        finally {
            Hooks.resetOnOperatorDebug();
        }
    }
}
