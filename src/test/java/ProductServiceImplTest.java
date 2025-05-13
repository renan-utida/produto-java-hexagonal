package usecase;

import Modelo.Product;
import UseCase.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import UseCase.ProductRepository;
import UseCase.InMemoryProductRepository;

import static org.junit.Assert.*;

public class ProductServiceImplTest {
    private ProductServiceImpl productService;
    private ProductRepository productRepository;

    @Before
    public void setUp() {
        productRepository = new InMemoryProductRepository();
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    public void testRegisterProduct() {
        // Given
        String code = "TEST001";
        String name = "Produto de Teste";
        double price = 99.99;

        // When
        productService.registerProduct(code, name, price);
        Product product = productService.getProduct(code);

        // Then
        assertNotNull(product);
        assertEquals(code, product.getCode());
        assertEquals(name, product.getName());
        assertEquals(price, product.getPrice(), 0.001);
    }

    @Test
    public void testUpdateProductPrice() {
        // Given
        String code = "TEST002";
        String name = "Outro Produto de Teste";
        double initialPrice = 199.99;
        double newPrice = 149.99;

        productService.registerProduct(code, name, initialPrice);

        // When
        productService.updateProductPrice(code, newPrice);
        Product product = productService.getProduct(code);

        // Then
        assertNotNull(product);
        assertEquals(newPrice, product.getPrice(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateNonExistentProductPrice() {
        // When
        productService.updateProductPrice("NAO_EXISTENTE", 99.99);
        // Then espera-se IllegalArgumentException
    }
}
