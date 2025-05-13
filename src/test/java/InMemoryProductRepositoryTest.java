
import Modelo.Product;
import UseCase.InMemoryProductRepository;
import org.junit.Before;
import org.junit.Test;
import UseCase.ProductRepository;

import java.util.List;

import static org.junit.Assert.*;

public class InMemoryProductRepositoryTest {
    private ProductRepository repository;

    @Before
    public void setUp() {
        repository = new InMemoryProductRepository();
    }

    @Test
    public void testSaveAndFindByCode() {
        // Given
        Product product = new Product("TEST001", "Produto de Teste", 99.99);

        // When
        repository.save(product);
        Product found = repository.findByCode("TEST001");

        // Then
        assertNotNull(found);
        assertEquals(product.getCode(), found.getCode());
        assertEquals(product.getName(), found.getName());
        assertEquals(product.getPrice(), found.getPrice(), 0.001);
    }

    @Test
    public void testFindAll() {
        // Given
        Product product1 = new Product("TEST001", "Produto 1", 10.0);
        Product product2 = new Product("TEST002", "Produto 2", 20.0);

        // When
        repository.save(product1);
        repository.save(product2);
        List<Product> products = repository.findAll();

        // Then
        assertEquals(2, products.size());
        assertTrue(products.stream().anyMatch(p -> p.getCode().equals("TEST001")));
        assertTrue(products.stream().anyMatch(p -> p.getCode().equals("TEST002")));
    }
}