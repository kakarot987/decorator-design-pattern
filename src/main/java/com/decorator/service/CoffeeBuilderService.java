package com.decorator.service;

import com.decorator.decorator.*;
import com.decorator.model.CoffeeOrderRequest;
import com.decorator.model.CoffeeOrderResponse;
import com.decorator.model.CoffeeOrderResponse.DecoratorStep;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * CoffeeBuilderService — orchestrates Decorator Pattern application.
 *
 * Flow:
 *   1. Create a base Concrete Component (Espresso / SimpleCoffee)
 *   2. Dynamically wrap it in Concrete Decorators based on the request
 *   3. Return full chain trace for frontend visualization
 */
@Service
public class CoffeeBuilderService {

    public CoffeeOrderResponse buildOrder(CoffeeOrderRequest request) {
        // Step 1: Create base component
        Coffee coffee = createBase(request.getBaseType());

        List<DecoratorStep> chain = new ArrayList<>();
        chain.add(new DecoratorStep(
                "Base: " + coffee.getDescription(),
                coffee.getDescription(),
                coffee.getCost()
        ));

        // Step 2: Apply each decorator in order
        if (request.getAddons() != null) {
            for (String addon : request.getAddons()) {
                coffee = applyDecorator(coffee, addon);
                chain.add(new DecoratorStep(
                        "+" + capitalize(addon) + "Decorator",
                        coffee.getDescription(),
                        roundCost(coffee.getCost())
                ));
            }
        }

        // Step 3: Build response
        return new CoffeeOrderResponse(
                request.getBaseType(),
                request.getAddons(),
                coffee.getDescription(),
                roundCost(coffee.getCost()),
                chain
        );
    }

    private Coffee createBase(String baseType) {
        if ("espresso".equalsIgnoreCase(baseType)) {
            return new Espresso();
        }
        return new SimpleCoffee();
    }

    private Coffee applyDecorator(Coffee coffee, String addon) {
        return switch (addon.toLowerCase()) {
            case "milk"    -> new MilkDecorator(coffee);
            case "sugar"   -> new SugarDecorator(coffee);
            case "whip"    -> new WhipCreamDecorator(coffee);
            case "vanilla" -> new VanillaDecorator(coffee);
            default        -> coffee;
        };
    }

    private double roundCost(double cost) {
        return Math.round(cost * 100.0) / 100.0;
    }

    private String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
}
