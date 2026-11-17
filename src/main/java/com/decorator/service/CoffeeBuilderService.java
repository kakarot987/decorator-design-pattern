package com.decorator.service;

import com.decorator.decorator.*;
import com.decorator.model.CoffeeOrderRequest;
import com.decorator.model.CoffeeOrderResponse;
import com.decorator.model.CoffeeOrderResponse.DecoratorStep;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Applies concrete decorators in requested order and records each layer.
 * Invalid input must never silently alter the requested composition.
 */
@Service
public class CoffeeBuilderService {

    public CoffeeOrderResponse buildOrder(CoffeeOrderRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Coffee order is required");
        }

        Coffee coffee = createBase(request.getBaseType());
        List<DecoratorStep> chain = new ArrayList<>();
        List<String> normalizedAddons = new ArrayList<>();
        chain.add(new DecoratorStep("Base: " + coffee.getDescription(),
                coffee.getDescription(), roundCost(coffee.getCost())));

        if (request.getAddons() != null) {
            for (String addon : request.getAddons()) {
                String name = normalizeAddon(addon);
                coffee = applyDecorator(coffee, name);
                normalizedAddons.add(name);
                chain.add(new DecoratorStep(
                        "+" + capitalize(name) + "Decorator",
                        coffee.getDescription(),
                        roundCost(coffee.getCost())
                ));
            }
        }

        return new CoffeeOrderResponse(
                request.getBaseType().trim().toLowerCase(Locale.ROOT),
                List.copyOf(normalizedAddons),
                coffee.getDescription(),
                roundCost(coffee.getCost()),
                chain
        );
    }

    private Coffee createBase(String baseType) {
        if (baseType == null || baseType.isBlank()) {
            throw new IllegalArgumentException("Base coffee type is required");
        }
        return switch (baseType.trim().toLowerCase(Locale.ROOT)) {
            case "espresso" -> new Espresso();
            case "simple" -> new SimpleCoffee();
            default -> throw new IllegalArgumentException(
                    "Unknown coffee base: " + baseType + ". Use espresso or simple");
        };
    }

    private String normalizeAddon(String addon) {
        if (addon == null || addon.isBlank()) {
            throw new IllegalArgumentException("Add-on must not be null or blank");
        }
        String name = addon.trim().toLowerCase(Locale.ROOT);
        if (!List.of("milk", "sugar", "whip", "vanilla").contains(name)) {
            throw new IllegalArgumentException("Unknown add-on: " + addon);
        }
        return name;
    }

    private Coffee applyDecorator(Coffee coffee, String addon) {
        return switch (addon) {
            case "milk" -> new MilkDecorator(coffee);
            case "sugar" -> new SugarDecorator(coffee);
            case "whip" -> new WhipCreamDecorator(coffee);
            case "vanilla" -> new VanillaDecorator(coffee);
            default -> throw new IllegalArgumentException("Unknown add-on: " + addon);
        };
    }

    private double roundCost(double cost) {
        return Math.round(cost * 100.0) / 100.0;
    }

    private String capitalize(String s) {
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
}
