package rs.ac.uns.ftn.gateway.gateway.config;


import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import rs.ac.uns.ftn.gateway.gateway.model.UniqueVisitor;
import rs.ac.uns.ftn.gateway.gateway.repository.UniqueVisitorRepository;

import java.time.Instant;
import java.util.*;

@Component
public class GlobalFilterUniqueVisitor implements GlobalFilter {


    @Autowired
    private UniqueVisitorRepository uniqueVisitorRepository;

    private Counter perCustomerMessages;

    @Autowired
    public void ReaderMessageReceiver(MeterRegistry meterRegistry) {
        this.perCustomerMessages = Metrics.counter("unique_user_counter");
    }

    @Override
    public Mono<Void> filter(
            ServerWebExchange exchange,
            GatewayFilterChain chain) {

        String address = Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getAddress().getHostAddress();
        String browser = exchange.getRequest().getHeaders().get("user-agent").get(0);
        long timeStampMillis = Instant.now().toEpochMilli();

        Optional<UniqueVisitor> retrievedUniqueVisitor = uniqueVisitorRepository
                .findAllByAddressAndBrowserAndTimestamp(address, browser, timeStampMillis);

        if (!retrievedUniqueVisitor.isPresent()) {
            System.out.println("DODAT NOVI UNIQUE_VISITOR");
            UniqueVisitor uniqueVisitor = new UniqueVisitor(address, browser, timeStampMillis);
            uniqueVisitorRepository.save(uniqueVisitor);
            this.perCustomerMessages.increment();
        }


        return chain.filter(exchange);
    }
}