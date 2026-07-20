package trigon.gestaofacil.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import trigon.gestaofacil.dto.CustomerRequestDTO;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  private String name;

  private String phone;
  private String address;

  @Column(precision = 10, scale = 2)
  private BigDecimal creditLimit;

  private Boolean active;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public Customer(CustomerRequestDTO request) {
    updateFromDTO(request);
    this.active = true;
  }

  public void updateFromDTO(CustomerRequestDTO request) {
    this.name = request.name();
    this.phone = request.phone();
    this.address = request.address();
    this.creditLimit = request.creditLimit();
  }

  @PrePersist
  public void prePersist() {
    createdAt = LocalDateTime.now();
    updatedAt = LocalDateTime.now();

    if (creditLimit == null) {
      this.creditLimit = BigDecimal.ZERO;
    }

    if (this.active == null) {
      this.active = true;
    }
  }

  @PreUpdate
  public void preUpdate() {
    this.updatedAt = LocalDateTime.now();
  }
  
}