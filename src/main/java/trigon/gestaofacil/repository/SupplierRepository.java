package trigon.gestaofacil.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import trigon.gestaofacil.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, UUID> {
  
  public List<Supplier> findByActiveTrue();
  
}