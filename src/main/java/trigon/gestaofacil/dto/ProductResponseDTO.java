package trigon.gestaofacil.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import trigon.gestaofacil.model.Product;

public record ProductResponseDTO(
  UUID id,
  String name,
  String description,
  String barcode,
  BigDecimal costPrice,
  BigDecimal salePrice,
  Integer currentStock,
  Integer minimumStock,
  Boolean active,
  LocalDateTime createdAt,
  LocalDateTime updatedAt
) {

  public ProductResponseDTO(Product product) {
    this(
      product.getId(),
      product.getName(),
      product.getDescription(),
      product.getBarcode(),
      product.getCostPrice(),
      product.getSalePrice(),
      product.getCurrentStock(),
      product.getMinimumStock(),
      product.getActive(),
      product.getCreatedAt(),
      product.getUpdatedAt()
    );
  }
}