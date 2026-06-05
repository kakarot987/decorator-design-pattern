package com.decorator.decorator;

import com.decorator.service.Coffee;

/**
 * Concrete Decorator — adds Vanilla syrup to a coffee.
 */
public class VanillaDecorator extends CoffeeDecorator {

    public VanillaDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", Vanilla";
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 0.35;
    }
}
