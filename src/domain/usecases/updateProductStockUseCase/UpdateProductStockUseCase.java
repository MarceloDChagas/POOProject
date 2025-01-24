package domain.usecases.updateProductStockUseCase;

import adapters.persistence.file.FileProductRepository;
import domain.entity.Product;

import java.util.List;

public class UpdateProductStockUseCase {
    private final FileProductRepository productRepository;

    public UpdateProductStockUseCase(FileProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void execute(String productId, int newStock) {
        // Carrega os produtos
        List<Product> products = productRepository.findAll();

        // Procura o produto pelo ID
        for (Product product : products) {
            if (product.getId().equals(productId)) {
                product.setStock(newStock);
                productRepository.saveAll(products);
                System.out.println("Estoque atualizado para o produto: " + product);
                return;
            }
        }

        System.out.println("Produto com ID " + productId + " não encontrado.");
    }
}
