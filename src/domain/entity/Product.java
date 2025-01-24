package domain.entity;

import security.IDGenerator;

public class Product {
    private String id;
    private String name;
    private double price;
    private int stock;
    private String description;
    private int salesCount;

    public Product(String name, double price, int stock, String description) {
        this.id = IDGenerator.generateProductId();
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.salesCount = 0;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getSalesCount() {
        return salesCount;
    }

    public void updateStock(int quantity) {
        if (quantity < 0 && Math.abs(quantity) > this.stock) {
            throw new IllegalArgumentException("Estoque insuficiente.");
        }
        this.stock += quantity;
    }

    public void incrementSalesCount() {
        this.salesCount++;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + price + "," + stock + "," + description + "," + salesCount;
    }

    public static Product fromString(String line) {
        String[] parts = line.split(",");
        Product product = new Product(
                parts[1],                          // Nome
                Double.parseDouble(parts[2]),      // Preço
                Integer.parseInt(parts[3]),        // Estoque
                parts[4]                           // Descrição
        );
        product.id = parts[0];                 // ID
        product.salesCount = Integer.parseInt(parts[5]); // Contagem de vendas
        return product;
    }

}
