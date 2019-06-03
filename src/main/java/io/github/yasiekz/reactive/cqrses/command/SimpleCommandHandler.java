package io.github.yasiekz.reactive.cqrses.command;

import reactor.core.publisher.Mono;

public interface SimpleCommandHandler<C extends Command> {

    Mono<Void> handle(C command);
}
