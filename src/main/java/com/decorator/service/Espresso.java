package com.decorator.service;

/**
 * Concrete Component — the base coffee (Espresso).
 * This is the object that will be dynamically wrapped by decorators.
 */
public class Espresso implements Coffee {

    @Override
    public String getDescription() {
        return "Espresso";
    }

    @Override
    public double getCost() {
        return 1.50;
    }
}
