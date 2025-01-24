package domain.usecases.createCostumerUseCase;

import domain.entity.Customer;
import domain.repository.ICustomerRepository;

import java.util.List;

public class CreateCustomerUseCase {
    private final ICustomerRepository customerRepository;

    public CreateCustomerUseCase(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void execute(String name, String email) {
        // Carrega os clientes existentes
        List<Customer> customers = customerRepository.findAll();

        // Cria um novo cliente
        Customer newCustomer = new Customer(name, email);

        // Salva o cliente
        customerRepository.save(newCustomer);

        System.out.println("Cliente criado com sucesso: " + newCustomer);
    }
}
