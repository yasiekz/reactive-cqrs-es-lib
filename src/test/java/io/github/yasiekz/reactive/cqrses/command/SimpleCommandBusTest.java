package io.github.yasiekz.reactive.cqrses.command;

import static org.mockito.Mockito.*;

import io.github.yasiekz.reactive.cqrses.HandlerNotFoundException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class SimpleCommandBusTest {

    @Test
    @DisplayName("Should dispatch given command correctly")
    void testCorrectDispatching() {

        // given
        Command1 command1 = new Command1();

        SimpleCommandHandler<Command1> commandHandler1 = spy(new SimpleCommandHandler1());
        SimpleCommandHandler<Command2> commandHandler2 = spy(new SimpleCommandHandler2());

        SimpleCommandBus bus = new SimpleCommandBus(List.of(commandHandler1, commandHandler2));

        // when
        final Mono<Void> mono = bus.dispatch(command1);

        // then
        StepVerifier.create(mono).verifyComplete();
        verify(commandHandler1, times(1)).handle(command1);
    }

    @Test
    @DisplayName("Should throw exception when there is no handler to handle command")
    void testThrowExceptionWhereNoHandlerFound() {

        // given
        Command1 command1 = new Command1();

        SimpleCommandBus bus = new SimpleCommandBus(List.of());

        // when
        final Mono<Void> mono = bus.dispatch(command1);

        // then
        StepVerifier.create(mono).verifyError(HandlerNotFoundException.class);
    }
}