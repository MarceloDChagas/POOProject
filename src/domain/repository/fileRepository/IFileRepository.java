package domain.repository.fileRepository;

import java.util.List;
import domain.entity.Product;

public interface IFileRepository {
    List<String> readFile(String filePath);  // Leitura genérica do arquivo
    void writeFile(String filePath, List<String> lines);  // Escrita genérica no arquivo
    void saveProducts(String filePath, List<Product> products);
    List<Product> loadProducts(String filePath);
}

