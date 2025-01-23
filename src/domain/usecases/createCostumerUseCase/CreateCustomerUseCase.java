package domain.usecases.createCostumerUseCase;

import adapters.persistence.file.FileCustomerRepository;
import domain.entity.Customer;

import java.util.List;

public class CreateCustomerUseCase {
    private final FileCustomerRepository customerRepository;

    public CreateCustomerUseCase(FileCustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void execute(String name, String email) {
        // Carrega os clientes existentes
        List<Customer> customers = customerRepository.loadCustomers();

        // Cria um novo cliente
        Customer newCustomer = new Customer(name, email);

        // Adiciona o cliente
        customers.add(newCustomer);

        // Salva os clientes atualizados
        customerRepository.saveCustomers(customers);

        System.out.println("Cliente criado com sucesso: " + newCustomer);
    }
}
