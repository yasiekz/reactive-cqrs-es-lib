package io.github.yasiekz.reactive.cqrses;

public class HandlerNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1911661903516814195L;

    public HandlerNotFoundException(final String message) {
        super(message);
    }
}
