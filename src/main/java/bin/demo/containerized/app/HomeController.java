package bin.demo.containerized.app;

import org.springframework.web.bind.annotation.RestController;

import bin.demo.containerized.app.models.Product;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HomeController {
    private final ProductRepository productRepository;

    public HomeController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping()
    public List<Product> listProducts() {
        return this.productRepository.findAll();
    }
}
