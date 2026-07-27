package com.sunanda.loanflow.gateway.controller;

import com.sunanda.loanflow.gateway.model.GatewayStatusResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

/**
 * Provides a simple endpoint to verify that the API Gateway is running.
 */
@RestController
@RequestMapping("/api/gateway")
public class GatewayStatusController {

    @GetMapping("/status")
    public ResponseEntity<GatewayStatusResponse> getStatus() {

        GatewayStatusResponse response = new GatewayStatusResponse(
                "api-gateway",
                "UP",
                Instant.now()
        );

        return ResponseEntity.ok(response);
    }
}