package trigon.gestaofacil.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import trigon.gestaofacil.model.Product;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}