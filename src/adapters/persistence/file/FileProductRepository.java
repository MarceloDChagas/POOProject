package adapters.persistence.file;

import domain.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class FileProductRepository extends FileRepository {
    private final String filePath;

    public FileProductRepository(String filePath) {
        this.filePath = filePath;
    }

    public void saveProducts(List<Product> products) {
        List<String> lines = new ArrayList<>();
        for (Product product : products) {
            lines.add(product.toString());
        }
        writeFile(filePath, lines);
    }

    public List<Product> loadProducts() {
        List<Product> products = new ArrayList<>();
        List<String> lines = readFile(filePath);
        for (String line : lines) {
            products.add(Product.fromString(line));
        }
        return products;
    }
}
