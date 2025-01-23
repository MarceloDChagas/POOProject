package domain.repository;
import domain.entity.Product;
import java.util.List;

public interface IProductRepository {

    void saveProduct(Product product);
    Product findProductById(int id);
    List<Product> findAllProducts();
    void updateProduct(Product product);
    void deleteProduct(int id);


}
