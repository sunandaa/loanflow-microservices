package com.sunanda.loanflow.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Adds or propagates a correlation ID for every request passing through the API
 * Gateway.
 */
@Component
public class CorrelationIdFilter implements GlobalFilter, Ordered {

	public static final String CORRELATION_ID_HEADER = "X-Correlation-Id";

	private static final Logger LOGGER = LoggerFactory.getLogger(CorrelationIdFilter.class);

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

		String correlationId = resolveCorrelationId(exchange.getRequest().getHeaders());

		ServerWebExchange mutatedExchange = exchange.mutate()
				.request(request -> request.headers(headers -> headers.set(CORRELATION_ID_HEADER, correlationId)))
				.build();

		mutatedExchange.getResponse().getHeaders().set(CORRELATION_ID_HEADER, correlationId);

		String method = mutatedExchange.getRequest().getMethod().name();

		String path = mutatedExchange.getRequest().getURI().getPath();

		long startTime = System.currentTimeMillis();

		LOGGER.info("Gateway request started: correlationId={}, method={}, path={}", correlationId, method, path);

		return chain.filter(mutatedExchange)
				.doOnSuccess(ignored -> logCompletion(mutatedExchange, correlationId, method, path, startTime))
				.doOnError(error -> LOGGER.error(
						"Gateway request failed: correlationId={}, " + "method={}, path={}, error={}", correlationId,
						method, path, error.getMessage()));
	}

	private String resolveCorrelationId(HttpHeaders headers) {

		String incomingCorrelationId = headers.getFirst(CORRELATION_ID_HEADER);

		if (StringUtils.hasText(incomingCorrelationId)) {
			return incomingCorrelationId.trim();
		}

		return UUID.randomUUID().toString();
	}

	private void logCompletion(ServerWebExchange exchange, String correlationId, String method, String path,
			long startTime) {

		long duration = System.currentTimeMillis() - startTime;

		Integer statusCode = exchange.getResponse().getStatusCode() == null ? null
				: exchange.getResponse().getStatusCode().value();

		LOGGER.info("Gateway request completed: correlationId={}, " + "method={}, path={}, status={}, durationMs={}",
				correlationId, method, path, statusCode, duration);
	}

	@Override
	public int getOrder() {
		return Ordered.HIGHEST_PRECEDENCE;
	}
}