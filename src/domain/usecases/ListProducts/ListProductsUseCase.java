package domain.usecases.ListProducts;

import adapters.persistence.file.FileProductRepository;
import domain.entity.Product;

import java.util.List;

public class ListProductsUseCase {
    private final FileProductRepository productRepository;

    public ListProductsUseCase(FileProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void execute() {
        // Carrega os produtos
        List<Product> products = productRepository.loadProducts();

        // Exibe os produtos
        if (products.isEmpty()) {
            System.out.println("Nenhum produto encontrado.");
        } else {
            System.out.println("Produtos cadastrados:");
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }
}
