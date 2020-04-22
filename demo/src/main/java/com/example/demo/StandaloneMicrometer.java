package com.example.demo;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.time.Duration;

import com.sun.net.httpserver.HttpServer;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.micrometer.jmx.JmxConfig;
import io.micrometer.jmx.JmxMeterRegistry;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.micrometer.shaded.reactor.core.publisher.Flux;
import io.micrometer.shaded.reactor.util.annotation.Nullable;

public class StandaloneMicrometer {
	public static void main(String[] args) {
		CompositeMeterRegistry registry = new CompositeMeterRegistry();
		registry.add(StandaloneMicrometer.jmx());
		registry.add(StandaloneMicrometer.prometheus());
		Counter ping = registry.counter("my.counter", "type", "ping");
		Counter pong = Counter.builder("my.counter").tag("type", "pong").register(registry);
		Flux.interval(Duration.ofMillis(10)).doOnEach(i -> ping.increment()).subscribe();
		Flux.interval(Duration.ofMillis(5)).doOnEach(i -> pong.increment()).blockLast();

	}

	public static JmxMeterRegistry jmx() {
        return new JmxMeterRegistry(new JmxConfig() {
            @Override
            public Duration step() {
                return Duration.ofSeconds(100);
            }

            @Override
            @Nullable
            public String get(String k) {
                return null;
            }
        }, Clock.SYSTEM);
    }
	
	 public static PrometheusMeterRegistry prometheus() {
	        PrometheusMeterRegistry prometheusRegistry = new PrometheusMeterRegistry(k -> null);

	        try {
	            HttpServer server = HttpServer.create(new InetSocketAddress(8090), 0);
	            server.createContext("/prometheus", httpExchange -> {
	                String response = prometheusRegistry.scrape();
	                httpExchange.sendResponseHeaders(200, response.length());
	                OutputStream os = httpExchange.getResponseBody();
	                os.write(response.getBytes());
	                os.close();
	            });

	            new Thread(server::start).run();
	        } catch (IOException e) {
	            throw new RuntimeException(e);
	        }

	        return prometheusRegistry;
	    }
}
