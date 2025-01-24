package domain.repository;

import domain.entity.Product;

import java.util.List;

public interface IProductRepository {
    void save(Product product);                 // Salva ou atualiza um produto
    Product findById(String id);                // Encontra um produto pelo ID
    List<Product> findAll();                    // Retorna todos os produtos
    void delete(String id);                     // Remove um produto pelo ID
    void saveAll(List<Product> products);       // Salva todos os produtos
}