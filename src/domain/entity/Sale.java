package domain.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.StringJoiner;

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

    public Sale(LocalDate date, int quantity) {
        this.id = IDGenerator.generateUniqueId();
        this.quantity = quantity;
        this.date = LocalDate.now();
        product.incrementSalesCount();
    }

    public static Sale fromString(String line) {
        String[] parts = line.split(",");
        // Converter cada elemento para String (caso não seja)
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].toString(); // Converte explicitamente para String
        }
        //parts[0] = id venda
        //parts[1] até parts [6] = product
        //parts[7] = quantidade
        //parts[8] = total
        //parts[9] = date
        //parts[10] até parts[14] = costumer
        int quantity = Integer.parseInt(parts[7]);

        Product product = new Product(parts[2],Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), parts[5]);
        Customer customer = new Customer(parts[11], parts[12]);
        Sale sale = new Sale(product, quantity, customer);
        return sale;
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
