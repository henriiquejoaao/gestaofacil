package trigon.gestaofacil.dto;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import trigon.gestaofacil.enums.StockMovementReason;

public record StockMovementRequestDTO(
  
  @NotNull(message = "O produto é obrigatório.")
  UUID productId,

  @NotNull(message = "A quantidade é obrigatória.")
  @Min(value = 1, message = "A quantidade deve ser maior que zero.")
  Integer quantity,

  @NotNull(message = "O motivo é obrigatório.")
  StockMovementReason reason
  
) {}