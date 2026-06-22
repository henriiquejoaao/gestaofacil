package trigon.gestaofacil.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import trigon.gestaofacil.enums.StockMovementReason;
import trigon.gestaofacil.enums.StockMovementType;
import trigon.gestaofacil.model.StockMovement;

public record StockMovementResponseDTO(
  UUID id,
  UUID productId,
  String productName,
  StockMovementType type,
  Integer quantity,
  StockMovementReason reason,
  LocalDateTime createdAt
) {

  public StockMovementResponseDTO(StockMovement movement) {
    this(
      movement.getId(),
      movement.getProduct().getId(),
      movement.getProduct().getName(),
      movement.getType(),
      movement.getQuantity(),
      movement.getReason(),
      movement.getCreatedAt()
    );
  }
}