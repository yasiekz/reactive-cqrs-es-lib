package io.github.yasiekz.reactive.cqrses.command;

import reactor.core.publisher.Mono;

public class SimpleCommandHandler1 implements SimpleCommandHandler<Command1> {

    @Override
    public Mono<Void> handle(final Command1 command) {
        return Mono.empty();
    }
}
