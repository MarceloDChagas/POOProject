package adapters.persistence.file;

import domain.entity.Product;
import domain.repository.IProductRepository;

import java.util.ArrayList;
import java.util.List;

public class FileProductRepository extends FileRepository implements IProductRepository {
    private final String filePath = System.getProperty("user.dir") + "/src/adapters/persistence/file/product.txt";

    @Override
    public void save(Product product) {
        // Carregar todos os produtos existentes
        List<Product> products = findAll();

        // Adicionar ou atualizar o produto
        products.removeIf(p -> p.getId().equals(product.getId()));
        products.add(product);

        // Salvar no arquivo
        saveAll(products);
    }

    @Override
    public Product findById(String id) {
        return findAll().stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        List<String> lines = readFile(filePath);

        for (String line : lines) {
            products.add(Product.fromString(line)); // Implementar método estático `fromString` na classe Product
        }
        return products;
    }

    @Override
    public void delete(String id) {
        List<Product> products = findAll();
        products.removeIf(product -> product.getId().equals(id));
        saveAll(products);
    }

    @Override
    public void saveAll(List<Product> products) {
        List<String> lines = new ArrayList<>();
        for (Product product : products) {
            lines.add(product.toString());
        }
        writeFile(filePath, lines);
    }
}
