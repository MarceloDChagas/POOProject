package adapters.persistence.file;

import domain.entity.Customer;
import domain.repository.ICustomerRepository;

import java.util.ArrayList;
import java.util.List;

public class FileCustomerRepository extends FileRepository implements ICustomerRepository {
    private final String filePath = System.getProperty("user.dir") + "/src/adapters/persistence/file/customer";

    @Override
    public void save(Customer customer) {
        List<Customer> customers = findAll(); // Carrega todos os clientes
        // Verifica se o cliente já existe (atualização)
        boolean updated = false;
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId().equals(customer.getId())) {
                customers.set(i, customer); // Atualiza o cliente
                updated = true;
                break;
            }
        }
        if (!updated) {
            customers.add(customer); // Adiciona um novo cliente
        }
        saveCustomers(customers); // Salva todos os clientes no arquivo
    }

    @Override
    public Customer findById(String id) {
        return findAll().stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst()
                .orElse(null); // Retorna null se não encontrar
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        List<String> lines = readFile(filePath);

        for (String line : lines) {
            try {
                customers.add(Customer.fromString(line));
            } catch (IllegalArgumentException e) {
                System.err.println("Linha inválida ignorada: " + line);
            }
        }

        return customers;
    }


    @Override
    public void update(Customer customer) {
        save(customer); // A lógica de atualização já está no método save
    }

    @Override
    public void delete(String id) {
        List<Customer> customers = findAll();
        customers.removeIf(customer -> customer.getId().equals(id)); // Remove o cliente com o ID
        saveCustomers(customers); // Salva a lista atualizada
    }

    @Override
    public Customer findByEmail(String email) {
        return findAll().stream()
                .filter(customer -> customer.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Customer> findByName(String name) {
        List<Customer> result = new ArrayList<>();
        for (Customer customer : findAll()) {
            if (customer.getName().contains(name)) {
                result.add(customer);
            }
        }
        return result;
    }

    @Override
    public double moneySpent(Customer customer) {
        return customer.getTotalMoneySpent(); // Supondo que Customer armazena essa informação
    }

    @Override
    public int timesPurchased(Customer customer) {
        return customer.getPurchaseCount(); // Supondo que Customer armazena essa informação
    }

    private void saveCustomers(List<Customer> customers) {
        List<String> lines = new ArrayList<>();
        for (Customer customer : customers) {
            lines.add(customer.toString()); // Converte para String
        }
        writeFile(filePath, lines); // Salva no arquivo
    }

}
