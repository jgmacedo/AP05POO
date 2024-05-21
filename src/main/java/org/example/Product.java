package org.example;

public class Product {
    private String type;
    private String description;
    private double weight;
    private int quantity;
    private String unitOfMeasure;

    public Product(String type, String description, double weight, int quantity, String unitOfMeasure) {
        this.type = type;
        this.description = description;
        this.weight = weight;
        this.quantity = quantity;
        setUnitOfMeasure(unitOfMeasure);
    }

    // Getters e Setters

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        if (unitOfMeasure.equals("metro") || unitOfMeasure.equals("metro quadrado") ||
                unitOfMeasure.equals("litro") || unitOfMeasure.equals("kg")) {
            this.unitOfMeasure = unitOfMeasure;
        } else {
            throw new IllegalArgumentException("Unidade de medida inválida!");
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", weight=" + weight +
                ", quantity=" + quantity +
                ", unitOfMeasure='" + unitOfMeasure + '\'' +
                '}';
    }
}