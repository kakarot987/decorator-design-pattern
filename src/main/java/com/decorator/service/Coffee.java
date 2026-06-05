package com.decorator.service;

/**
 * Component Interface — defines the contract for all coffee types.
 * Both concrete components and decorators implement this interface.
 */
public interface Coffee {
    String getDescription();
    double getCost();
}
