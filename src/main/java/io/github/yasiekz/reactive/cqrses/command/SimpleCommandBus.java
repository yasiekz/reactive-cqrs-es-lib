package io.github.yasiekz.reactive.cqrses.command;

import io.github.yasiekz.reactive.cqrses.HandlerNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.core.GenericTypeResolver;
import reactor.core.publisher.Mono;

@SuppressWarnings("WeakerAccess")
public class SimpleCommandBus implements CommandDispatcher {

    private final Map<Class, SimpleCommandHandler> handlers;

    public SimpleCommandBus(final List<SimpleCommandHandler> handlers) {

        this.handlers = getHandlersWithCommands(handlers);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Mono<Void> dispatch(final Command command) {

        return Mono.justOrEmpty(handlers.get(command.getClass()))
            .switchIfEmpty(
                Mono.error(new HandlerNotFoundException("There is no handler for " + command.getClass().toString())))
            .flatMap(h -> h.handle(command))
            .then();
    }

    private Map<Class, SimpleCommandHandler> getHandlersWithCommands(
        List<SimpleCommandHandler> handlers) {

        final HashMap<Class, SimpleCommandHandler> map = new HashMap<>();

        handlers
            .forEach(
                h -> map.put(GenericTypeResolver.resolveTypeArgument(h.getClass(), SimpleCommandHandler.class), h));

        return map;
    }
}
