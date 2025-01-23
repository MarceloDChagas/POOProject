package domain.entity;

import java.time.LocalDate;
import security.IDGenerator;

public class Sale {
    private String id;
    private Product product;
    private int quantity;
    private double totalAmount;
    private LocalDate date;
    private Customer customer;

    public Sale(Product product, int quantity, Customer customer) {
        this.id = IDGenerator.generateUniqueId();
        this.product = product;
        this.quantity = quantity;
        this.customer = customer;
        this.totalAmount = product.getPrice() * quantity;
        this.date = LocalDate.now();
        product.incrementSalesCount(); // Incrementa a contagem de vendas do produto
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public LocalDate getDate() {
        return date;
    }

    public Customer getCustomer() {
        return customer;
    }
}
