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
    public Customer(String id, String name, String email, double moneySpent, int timesPurchased) {
        this.id = IDGenerator.generateUniqueId();
        this.name = name;
        this.email = email;
        this.timesPurchased = 0;
        this.moneySpent = 0;
    }

    public static Customer fromString(String line) {
        String[] parts = line.split(",");

        // Validação para garantir que existem pelo menos 5 partes
        if (parts.length < 5) {
            throw new IllegalArgumentException("Formato inválido para a string de cliente: " + line);
        }

        String id = parts[0];  // ID do cliente
        String name = parts[1];  // Nome do cliente
        String email = parts[2];  // Email do cliente
        double moneySpent = Double.parseDouble(parts[3]);  // Dinheiro gasto
        int timesPurchased = Integer.parseInt(parts[4]);  // Total de compras realizadas

        // Cria o cliente com os dados fornecidos
        Customer customer = new Customer(id, name, email, moneySpent, timesPurchased);
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
    @Override
    public String toString() {
        return String.format("%s,%s,%s,%.2f,%d",
                id,
                name,
                email,
                moneySpent,
                timesPurchased);
    }

}
