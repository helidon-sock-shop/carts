package io.helidon.examples.sockshop.carts.redis;

import io.helidon.examples.sockshop.carts.CartRepository;
import io.helidon.examples.sockshop.carts.CartRepositoryTest;

import static io.helidon.examples.sockshop.carts.redis.RedisProducers.carts;
import static io.helidon.examples.sockshop.carts.redis.RedisProducers.client;

/**
 * Tests for Redis repository implementation.
 */
class RedisCartRepositoryIT extends CartRepositoryTest {
    public CartRepository getCartRepository() {
        String host = System.getProperty("db.host","localhost");
        int    port = Integer.parseInt(System.getProperty("db.port","6379"));

        return new RedisCartRepository(carts(client(host, port)));
    }
}
