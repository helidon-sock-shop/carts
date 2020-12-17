/*
 * Copyright (c) 2020 Oracle and/or its affiliates.
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at
 * http://oss.oracle.com/licenses/upl.
 */

package io.helidon.examples.sockshop.carts.coherence;

import javax.enterprise.inject.spi.CDI;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import io.helidon.examples.sockshop.carts.CartRepository;
import io.helidon.examples.sockshop.carts.CartRepositoryAsync;
import io.helidon.examples.sockshop.carts.CartRepositoryTest;
import io.helidon.examples.sockshop.carts.SyncCartRepository;

import io.helidon.microprofile.server.Server;

/**
 * Tests for Coherence repository implementation.
 */
class CoherenceCartRepositoryAsyncIT extends CartRepositoryTest {
    protected static Server SERVER;

    /**
     * This will start the application on ephemeral port to avoid port conflicts.
     * We can discover the actual port by calling {@link io.helidon.microprofile.server.Server#port()} method afterwards.
     */
    @BeforeAll
    static void startServer() {
        // disable global tracing so we can start server in multiple test suites
        System.setProperty("tracing.global", "false");
        SERVER = Server.builder().port(0).build().start();
    }

    /**
     * Stop the server, as we cannot have multiple servers started at the same time.
     */
    @AfterAll
    static void stopServer() {
        SERVER.stop();
    }

    @Override
    protected CartRepository getCartRepository() {
        CartRepositoryAsync cartsAsync = CDI.current().select(CartRepositoryAsync.class).get();
        return new SyncCartRepository(cartsAsync);
    }
}
