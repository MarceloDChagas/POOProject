package domain.repository;

import domain.entity.Customer;
import java.util.List;

public interface ICustomerRepository {
    // CRUD básico
    void save(Customer customer);            // Salva um novo cliente ou atualiza um cliente existente
    Customer findById(int id);               // Encontra um cliente pelo ID
    List<Customer> findAll();                // Retorna todos os clientes
    void update(Customer customer);          // Atualiza um cliente existente
    void delete(int id);                     // Deleta um cliente pelo ID

    Customer findByEmail(String email);
    List<Customer> findByName(String name);
    double moneySpent(Customer customer); // Quem mais gastou
    int timesPurchased(Customer customer); //Quem mais comprou

}