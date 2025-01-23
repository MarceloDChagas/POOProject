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

    public int getTimesPurchased() {
        return timesPurchased;
    }

    public double getMoneySpent() {
        return moneySpent;
    }

}
