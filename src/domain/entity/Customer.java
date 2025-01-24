package domain.entity;

import security.IDGenerator;

public class Customer {
    private String id;
    private String name;
    private String email;
    private int timesPurchased;
    private double moneySpent;

    public Customer(String name, String email) {
        this.id = IDGenerator.generateUniqueId();
        this.name = name;
        this.email = email;
        this.timesPurchased = 0;
        this.moneySpent = 0;
    }

    public static Customer fromString(String line) {
        String[] parts = line.split(",");
        String name = parts[1];
        String email = parts[2];
        Customer customer = new Customer(name, email);
        return customer;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getTotalMoneySpent() {
        return this.moneySpent;
    }

    public int getPurchaseCount() {
        return this.timesPurchased;
    }
}
