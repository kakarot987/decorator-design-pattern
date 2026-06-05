package com.decorator.service;

/**
 * Concrete Component — a plain brewed coffee.
 */
public class SimpleCoffee implements Coffee {

    @Override
    public String getDescription() {
        return "Simple Coffee";
    }

    @Override
    public double getCost() {
        return 1.00;
    }
}
