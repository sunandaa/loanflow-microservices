package com.sunanda.loanflow.gateway.model;

import java.time.Instant;

/**
 * Response returned by the gateway status endpoint.
 *
 * @param application application name
 * @param status      current status
 * @param timestamp   response generation time
 */
public record GatewayStatusResponse(
        String application,
        String status,
        Instant timestamp
) {
}