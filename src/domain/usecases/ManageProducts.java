package domain.usecases;

import domain.entity.Product;
import domain.repository.IProductRepository;
import java.util.List;

public class ManageProducts {
    private final IProductRepository productRepository;
    public ManageProducts(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public void addProduct(Product product) {
        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero.");
        }
        productRepository.save(product);
    }

    public Product getProductById(String id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void deleteProduct(String id) {
        productRepository.delete(id);
    }

}
