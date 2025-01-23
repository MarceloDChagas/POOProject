package domain.usecases.createProduct;

import adapters.persistence.file.FileProductRepository;
import domain.entity.Product;

import java.util.List;

public class CreateProductUseCase {
    private final FileProductRepository productRepository;

    public CreateProductUseCase(FileProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void execute(String name, double price, int stock, String description) {
        // Carrega os produtos existentes
        List<Product> products = productRepository.loadProducts();

        // Cria um novo produto
        Product newProduct = new Product(name, price, stock, description);

        // Adiciona o novo produto
        products.add(newProduct);

        // Salva os produtos atualizados
        productRepository.saveProducts(products);

        System.out.println("Produto criado com sucesso: " + newProduct);
    }
}
