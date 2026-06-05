package com.decorator.decorator;

import com.decorator.service.Coffee;

/**
 * Abstract Decorator — wraps a Coffee object and delegates calls to it.
 * All concrete decorators extend this class.
 *
 * Key: it holds a reference to the wrapped component (composition over inheritance).
 */
public abstract class CoffeeDecorator implements Coffee {

    protected final Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription();
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost();
    }
}
