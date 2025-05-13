package UseCase;
import Modelo.Product;
import UseCase.ProductService;
import UseCase.ProductRepository;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void registerProduct(String code, String name, double price) {
        // Verifica se já existe produto com o mesmo código
        Product existingProduct = productRepository.findByCode(code);
        if (existingProduct != null) {
            throw new IllegalArgumentException("Produto com código " + code + " já existe");
        }

        Product product = new Product(code, name, price);
        productRepository.save(product);
    }

    @Override
    public void updateProductPrice(String code, double newPrice) {
        Product product = productRepository.findByCode(code);
        if (product != null) {
            product.setPrice(newPrice);
            productRepository.save(product);
        } else {
            throw new IllegalArgumentException("Produto com código " + code + " não encontrado");
        }
    }

    @Override
    public Product getProduct(String code) {
        return productRepository.findByCode(code);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
