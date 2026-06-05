package com.decorator.model;

import java.util.List;

public class CoffeeOrderRequest {
    private String baseType;       // "espresso" or "simple"
    private List<String> addons;   // ["milk", "sugar", "whip", "vanilla"]

    public String getBaseType() { return baseType; }
    public void setBaseType(String baseType) { this.baseType = baseType; }

    public List<String> getAddons() { return addons; }
    public void setAddons(List<String> addons) { this.addons = addons; }
}
