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

    // Construtor principal
    public Sale(Product product, int quantity, Customer customer) {
        this.id = IDGenerator.generateUniqueId();
        this.product = product;
        this.quantity = quantity;
        this.customer = customer;
        this.totalAmount = product.getPrice() * quantity;
        this.date = LocalDate.now();
        product.incrementSalesCount(); // Incrementa a contagem de vendas do produto
    }
    
    public Sale(LocalDate date, Product product, int quantity, Customer customer, double totalAmount) {
        this.id = IDGenerator.generateUniqueId(); // Gerar ID único
        this.date = date;
        this.product = product;
        this.quantity = quantity;
        this.customer = customer;
        this.totalAmount = totalAmount;
    }

    // Construtor adicional com todos os parâmetros
    public Sale(String id, LocalDate date, Product product, int quantity, Customer customer, double totalAmount) {
        this.id = id;
        this.date = date;
        this.product = product;
        this.quantity = quantity;
        this.customer = customer;
        this.totalAmount = totalAmount;
    }

    public Sale(LocalDate date, Product product, Customer customer, double totalAmount) {
        this.id = IDGenerator.generateUniqueId();
        this.date = date;
        this.product = product;
        this.customer = customer;
        this.totalAmount = totalAmount;
        this.quantity = 1; // Defina um valor padrão para a quantidade, ou ajuste conforme necessário
    }

    public Sale(LocalDate date, double totalAmount) {
        this.date = date;
        this.totalAmount = totalAmount;
    }

    public static Sale fromString(String line) {
        // Imprime a linha para depuração
        System.out.println("Lendo linha: " + line);

        // Divide a linha em partes
        String[] parts = line.split(",");

        // Verifica se há o número esperado de campos
        if (parts.length < 12) { // Esperamos 12 campos agora
            throw new IllegalArgumentException("Linha inválida ou incompleta: " + line);
        }

        try {
            // Extrai os campos necessários
            String saleId = parts[0]; // ID da venda (opcional, pode ser ignorado)
            Sale sale = getSale(parts);
            sale.id = saleId; // Define o ID da venda, caso necessário
            return sale;

        } catch (Exception e) {
            // Lança uma exceção com informações detalhadas
            throw new IllegalArgumentException("Erro ao processar linha: " + line, e);
        }
    }

    private static Sale getSale(String[] parts) {
        int quantity = Integer.parseInt(parts[6]); // Quantidade vendida
        double totalAmount = Double.parseDouble(parts[7]); // Total da venda
        LocalDate date = LocalDate.parse(parts[8]); // Data da venda

        // Criação do produto
        String productName = parts[2];
        double productPrice = Double.parseDouble(parts[3]);
        int productStock = Integer.parseInt(parts[4]);
        String productDescription = parts[5];
        Product product = new Product(productName, productPrice, productStock, productDescription);

        // Criação do cliente
        String customerId = parts[9]; // ID do cliente (opcional, pode ser ignorado)
        String customerName = parts[10];
        String customerEmail = parts[11];
        Customer customer = new Customer(customerName, customerEmail);

        // Retorna a venda criada
        Sale sale = new Sale(date, product, quantity, customer, totalAmount);
        return sale;
    }

    // Getters
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

    // toString para salvar no arquivo
    @Override
    public String toString() {
        return String.join(",",
                id,
                product.getId(),
                product.getName(),
                String.valueOf(product.getPrice()),
                String.valueOf(product.getStock()),
                product.getDescription(),
                String.valueOf(quantity),
                String.valueOf(totalAmount),
                date.toString(),
                customer.getId(),
                customer.getName(),
                customer.getEmail()
        );
    }
}
