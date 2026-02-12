package bin.demo.containerized.app;

import org.springframework.data.jpa.repository.JpaRepository;

import bin.demo.containerized.app.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
