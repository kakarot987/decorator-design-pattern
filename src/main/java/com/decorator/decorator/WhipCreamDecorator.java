package com.decorator.decorator;

import com.decorator.service.Coffee;

/**
 * Concrete Decorator — adds Whipped Cream to a coffee.
 */
public class WhipCreamDecorator extends CoffeeDecorator {

    public WhipCreamDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", Whip Cream";
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost() + 0.50;
    }
}
