package adapters.persistence.file;

import domain.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class FileCustomerRepository extends FileRepository {
    private final String filePath;

    public FileCustomerRepository(String filePath) {
        this.filePath = filePath;
    }

    public void saveCustomers(List<Customer> customers) {
        List<String> lines = new ArrayList<>();
        for (Customer customer : customers) {
            lines.add(customer.toString());
        }
        writeFile(filePath, lines);
    }

    public List<Customer> loadCustomers() {
        List<Customer> customers = new ArrayList<>();
        List<String> lines = readFile(filePath);
        for (String line : lines) {
            customers.add(Customer.fromString(line));
        }
        return customers;
    }
}
