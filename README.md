[![Build Status](https://api.travis-ci.com/yasiekz/reactive-cqrs-es-lib.svg?branch=master)](https://travis-ci.com/yasiekz/reactive-cqrs-es-java)
[![codecov](https://codecov.io/gh/yasiekz/reactive-cqrs-es-lib/branch/master/graph/badge.svg)](https://codecov.io/gh/yasiekz/reactive-cqrs-es-lib)

# Reactive CQRS + ES lib

This is simple lib which adds reactive command handling classes to your springboot app

#### Key features:
- dispatches command to reactive handlers

#### Scheduled functionality
- Event bus
- Command bus with handler that could return event
- automatically apply event on bus

## Usage

##### Register bean in `@Configuration` class

```java
@Configuration
public class CqrsConfiguration {
    
    @Bean
    public CommandDispatcher createSimpleCommandBus(List<SimpleCommandHandler> handlers) {
        return new SimpleCommandBus(handlers);
    }
}
```

##### Create some commands

```java
import io.github.yasiekz.reactive.cqrses.command.Command;

public class DummyCommand extends Command {
    // some data here with getters
}
```

**Important**
Commands should be immutable!

##### Create handler for Command

```java
package io.github.yasiekz.reactive.cqrses.command.SimpleCommandHandler;

import reactor.core.publisher.Mono;

public class DummyCommandHandler implements SimpleCommandHandler<DummyCommand> {

    @Override
    public Mono<Void> handle(final DummyCommand command) {
        // do something with command using reactive flow
    }
}

```

## Local run

#### Requirements

- Java 11 to build project 
- Spring boot 2.1.x

#### Run tests

```bash
./gradlew check
```

