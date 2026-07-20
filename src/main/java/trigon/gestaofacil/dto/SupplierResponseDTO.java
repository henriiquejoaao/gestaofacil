package trigon.gestaofacil.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import trigon.gestaofacil.model.Supplier;

public record SupplierResponseDTO(

  UUID id,
  String name,
  String cnpj,
  String phone,
  String email,
  String address,
  Boolean active,
  LocalDateTime createdAt,
  LocalDateTime updatedAt

) {

  public SupplierResponseDTO(Supplier supplier) {
    this(
      supplier.getId(),
      supplier.getName(),
      supplier.getCnpj(),
      supplier.getPhone(),
      supplier.getEmail(),
      supplier.getAddress(),
      supplier.getActive(),
      supplier.getCreatedAt(),
      supplier.getUpdatedAt()
    );
  }
  
}
