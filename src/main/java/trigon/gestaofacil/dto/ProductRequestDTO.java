package trigon.gestaofacil.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ProductRequestDTO(

  @NotBlank(message="O nome é obrigatorio")
  String name,

  String description,

  String barcode,

  @DecimalMin(value="0.0", message="O preço de custo não pode ser negativo")
  BigDecimal costPrice,

  @DecimalMin(value="0.0", message="O preço de vanda não pode ser negativo")
  BigDecimal salePrice,

  @Min(value=0, message="O estoque atual não pode ser negativo")
  Integer currentStock,

  @Min(value=0, message="O estoque mínimo não pode ser negativo")
  Integer minimumStock,

  LocalDate expirationDate

) {}
