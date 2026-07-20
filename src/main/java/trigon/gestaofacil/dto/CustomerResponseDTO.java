package trigon.gestaofacil.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import trigon.gestaofacil.model.Customer;

public record CustomerResponseDTO(

  UUID id,
  String name,
  String phone,
  String address,
  BigDecimal creditLimit,
  Boolean active,
  LocalDateTime createdAt,
  LocalDateTime updatedAt

) {

  public CustomerResponseDTO(Customer customer) {
    this(
      customer.getId(),
      customer.getName(),
      customer.getPhone(),
      customer.getAddress(),
      customer.getCreditLimit(),
      customer.getActive(),
      customer.getCreatedAt(),
      customer.getUpdatedAt()
    );
  }

}
