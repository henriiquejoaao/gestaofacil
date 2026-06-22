package trigon.gestaofacil.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import trigon.gestaofacil.model.StockMovement;

public interface StockMovementRepository extends JpaRepository<StockMovement, UUID> {

  List<StockMovement> findTop10ByOrderByCreatedAtDesc();
  
  List<StockMovement> findByProductIdOrderByCreatedAtDesc(UUID productId);

  List<StockMovement> findByCreatedAtBetweenOrderByCreatedAtDesc(LocalDateTime start, LocalDateTime end
  );

  long countByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

}