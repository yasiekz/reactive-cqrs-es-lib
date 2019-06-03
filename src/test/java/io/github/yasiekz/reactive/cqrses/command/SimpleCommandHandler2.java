package io.github.yasiekz.reactive.cqrses.command;

import reactor.core.publisher.Mono;

public class SimpleCommandHandler2 implements SimpleCommandHandler<Command2> {

    @Override
    public Mono<Void> handle(final Command2 command) {
        return Mono.empty();
    }
}
