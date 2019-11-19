package com.mytests.reactor.reactor_tests;

import reactor.core.publisher.Flux;

public class ReactorDebuggerTestWithCheckpoint {
  public static void main(String[] args) {
    Flux.just(1, 2, 3)
      .checkpoint("bbbb", true)
      .map(i -> i + 2)
      .checkpoint("aaa")
      .filter(i -> true)
      .checkpoint()
      .map(i -> {

        return i + 4;
      }).subscribe();
  }
}
