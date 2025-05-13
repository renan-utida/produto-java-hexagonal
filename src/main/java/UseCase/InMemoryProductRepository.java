package UseCase;

import Modelo.Product;
import UseCase.ProductRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryProductRepository implements ProductRepository {
    private final Map<String, Product> storage = new HashMap<>();

    @Override
    public void save(Product product) {
        storage.put(product.getCode(), product);
    }

    @Override
    public Product findByCode(String code) {
        return storage.get(code);
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(storage.values());
    }
}
