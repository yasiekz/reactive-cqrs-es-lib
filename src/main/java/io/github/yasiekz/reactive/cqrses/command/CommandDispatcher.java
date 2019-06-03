package io.github.yasiekz.reactive.cqrses.command;

import reactor.core.publisher.Mono;

public interface CommandDispatcher {

    Mono<Void> dispatch(Command command);

}
