package trigon.gestaofacil.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import trigon.gestaofacil.model.Product;

public interface ProductRepository extends JpaRepository<Product, UUID> {

  Optional<Product> findByBarcode(String barcode);

  @Query("SELECT p FROM Product p WHERE p.currentStock <= p.minimumStock AND p.active = true")
  List<Product> findLowStockProducts();

  @Query("SELECT p FROM Product p WHERE p.currentStock = 0 AND p.active = true")
  List<Product> findOutOfStockProducts();
}