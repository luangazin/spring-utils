# Spring Utils

A Spring Boot utility library providing idempotency support for REST APIs using Redis cache.

## Features

- Idempotency handling using Redis cache
- Simple annotation-based implementation (`@Idempotent`)
- Configurable cache expiration time

## Prerequisites

- Java 17 or higher
- Redis server
- Spring Boot 3.x

## Installation

Add the following dependency to your `build.gradle.kts`:

```kotlin
dependencies {
    implementation("br.com.gazintech:spring-utils:1.0.0")
}
```

## Usage

1. Configure Redis in your `application.properties` or `application.yml`:

```yaml
spring:
  redis:
    host: localhost
    port: 6379
```

2. Add the `@Idempotent` annotation to your controller methods:

```kotlin
@PostMapping("/orders")
@Idempotent(cacheTimeSeconds = 3600) // Cache for 1 hour
fun createOrder(@RequestBody order: Order): OrderResponse {
    // Your implementation
}
```

3. Include the `Idempotency-Key` header in your HTTP requests:

```http
POST /orders
Idempotency-Key: 123e4567-e89b-12d3-a456-426614174000
Content-Type: application/json

{
    "data": "your-request-payload"
}
```

## How It Works

- The library intercepts requests to methods annotated with `@Idempotent`
- Checks for the presence of the `Idempotency-Key` header (UUID format)
- If a cached response exists for the key, returns it immediately
- Otherwise, executes the method and caches the response
- Responses are cached in Redis with configurable expiration time

## Error Handling

The library throws `IdempotencyKeyNotFoundException` when:
- The `Idempotency-Key` header is missing
- The header value is not a valid UUID

## License

This project is licensed under the MIT License.
