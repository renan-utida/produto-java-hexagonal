package UseCase;
import Modelo.Product;
import java.util.List;

public interface ProductService {
    void registerProduct(String code, String name, double price);
    void updateProductPrice(String code, double newPrice);
    Product getProduct(String code);
    List<Product> getAllProducts();
}
