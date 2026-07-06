package trigon.gestaofacil.dto;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record StockAdjustmentRequestDTO(
  @NotNull(message = "O produto é obrigatório.")
  UUID productId,

  @NotNull(message = "O novo estoque é obrigatório.")
  @Min(value = 0, message = "O estoque não pode ser negativo.")
  Integer newStock

) {}