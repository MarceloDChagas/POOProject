package domain.usecases.listCostumerUseCase;

import adapters.persistence.file.FileCustomerRepository;
import domain.entity.Customer;

import java.util.List;

public class ListCustomersUseCase {
    private final FileCustomerRepository customerRepository;

    public ListCustomersUseCase(FileCustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void execute() {
        // Carrega os clientes
        List<Customer> customers = customerRepository.loadCustomers();

        // Exibe os clientes
        if (customers.isEmpty()) {
            System.out.println("Nenhum cliente encontrado.");
        } else {
            System.out.println("Clientes cadastrados:");
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        }
    }
}
