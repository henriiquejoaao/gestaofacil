package trigon.gestaofacil.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

public record CustomerRequestDTO(

  @NotBlank
  String name,

  String phone,

  String address,

  @DecimalMin(value = "0.0", message = "O limite de crédito não pode ser negativo.")
  BigDecimal creditLimit

) {}