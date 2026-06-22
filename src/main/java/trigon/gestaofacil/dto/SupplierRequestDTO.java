package trigon.gestaofacil.dto;

import jakarta.validation.constraints.NotBlank;

public record SupplierRequestDTO(

  @NotBlank(message = "O nome do fornecedor é obrigatório")
  String name,

  String cnpj,
  String phone,
  String email,
  String address

) {}