package com.decorator.controller;

import com.decorator.model.CoffeeOrderRequest;
import com.decorator.model.CoffeeOrderResponse;
import com.decorator.service.CoffeeBuilderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * CoffeeController — exposes the Decorator Pattern demo via REST.
 *
 * Endpoints:
 *   POST /api/coffee/order   — Build a coffee with chosen add-ons
 *   GET  /api/coffee/options — Returns available bases and add-ons
 */
@RestController
@RequestMapping("/api/coffee")
@CrossOrigin(origins = "*")
public class CoffeeController {

    private final CoffeeBuilderService coffeeBuilderService;

    public CoffeeController(CoffeeBuilderService coffeeBuilderService) {
        this.coffeeBuilderService = coffeeBuilderService;
    }

    @PostMapping("/order")
    public ResponseEntity<CoffeeOrderResponse> order(@RequestBody CoffeeOrderRequest request) {
        CoffeeOrderResponse response = coffeeBuilderService.buildOrder(request);
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleInvalidOrder(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
    }

    @GetMapping("/options")
    public ResponseEntity<Map<String, Object>> options() {
        return ResponseEntity.ok(Map.of(
            "bases", List.of(
                Map.of("id", "espresso", "label", "Espresso", "price", 1.50),
                Map.of("id", "simple",   "label", "Simple Coffee", "price", 1.00)
            ),
            "addons", List.of(
                Map.of("id", "milk",    "label", "Milk",         "price", 0.25),
                Map.of("id", "sugar",   "label", "Sugar",        "price", 0.10),
                Map.of("id", "whip",    "label", "Whip Cream",   "price", 0.50),
                Map.of("id", "vanilla", "label", "Vanilla Syrup","price", 0.35)
            )
        ));
    }
}
