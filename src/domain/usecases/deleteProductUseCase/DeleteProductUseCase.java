package domain.usecases.deleteProductUseCase;

import adapters.persistence.file.FileProductRepository;
import domain.entity.Product;

import java.util.List;

public class DeleteProductUseCase {
    private final FileProductRepository productRepository;

    public DeleteProductUseCase(FileProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void execute(String productId) {
        // Carrega os produtos
        List<Product> products = productRepository.findAll();

        // Remove o produto com o ID correspondente
        boolean removed = products.removeIf(product -> product.getId().equals(productId));

        // Atualiza o repositório
        productRepository.saveAll(products);

        if (removed) {
            System.out.println("Produto com ID " + productId + " removido com sucesso.");
        } else {
            System.out.println("Produto com ID " + productId + " não encontrado.");
        }
    }
}
