package trigon.gestaofacil.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import trigon.gestaofacil.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, UUID>{
  
  List<Customer> findByActiveTrue();

}