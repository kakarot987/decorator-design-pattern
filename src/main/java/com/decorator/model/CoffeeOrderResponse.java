package com.decorator.model;

import java.util.List;

public class CoffeeOrderResponse {

    private String baseType;
    private List<String> addons;
    private String description;
    private double totalCost;
    private List<DecoratorStep> decoratorChain;

    public CoffeeOrderResponse() {}

    public CoffeeOrderResponse(String baseType, List<String> addons, String description,
                                double totalCost, List<DecoratorStep> decoratorChain) {
        this.baseType = baseType;
        this.addons = addons;
        this.description = description;
        this.totalCost = totalCost;
        this.decoratorChain = decoratorChain;
    }

    // Getters & Setters
    public String getBaseType() { return baseType; }
    public void setBaseType(String baseType) { this.baseType = baseType; }

    public List<String> getAddons() { return addons; }
    public void setAddons(List<String> addons) { this.addons = addons; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getTotalCost() { return totalCost; }
    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }

    public List<DecoratorStep> getDecoratorChain() { return decoratorChain; }
    public void setDecoratorChain(List<DecoratorStep> decoratorChain) { this.decoratorChain = decoratorChain; }

    /**
     * Represents one step in the decorator wrapping chain — shows how each decorator
     * adds to the description and cost incrementally.
     */
    public static class DecoratorStep {
        private String layer;
        private String descriptionSoFar;
        private double costSoFar;

        public DecoratorStep(String layer, String descriptionSoFar, double costSoFar) {
            this.layer = layer;
            this.descriptionSoFar = descriptionSoFar;
            this.costSoFar = costSoFar;
        }

        public String getLayer() { return layer; }
        public String getDescriptionSoFar() { return descriptionSoFar; }
        public double getCostSoFar() { return costSoFar; }
    }
}
