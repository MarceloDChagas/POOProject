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
        productRepository.saveProduct(product);
    }

    public Product getProductById(int id) {
        return productRepository.findProductById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAllProducts();
    }

    public void updateProduct(Product product) {
        productRepository.updateProduct(product);
    }

    public void deleteProduct(int id) {
        productRepository.deleteProduct(id);
    }

}
