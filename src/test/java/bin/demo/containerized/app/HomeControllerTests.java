package bin.demo.containerized.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import bin.demo.containerized.app.models.Product;

@ExtendWith(MockitoExtension.class)
class HomeControllerTests {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private HomeController homeController;

    @Test
    void testListProducts() {
        //
        Product product1 = new Product("Iphone", "Apple phone", 300.0);
        Product product2 = new Product("Samsung", "Samsung phone", 300.0);
        when(productRepository.findAll()).thenReturn(List.of(product1, product2));
        List<Product> actual = homeController.listProducts();
        assertEquals(2, actual.size());
        verify(productRepository).findAll();
    }
}
