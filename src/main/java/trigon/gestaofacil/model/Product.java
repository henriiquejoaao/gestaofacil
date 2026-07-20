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
import trigon.gestaofacil.dto.ProductRequestDTO;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String name;
  private String description;

  @Column(unique = true)
  private String barcode;

  private BigDecimal costPrice;
  private BigDecimal salePrice;

  private Integer currentStock;
  private Integer minimumStock;

  private Boolean active;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public Product(ProductRequestDTO request) {
    this.name = request.name();
    this.description = request.description();
    this.barcode = request.barcode();
    this.costPrice = request.costPrice();
    this.salePrice = request.salePrice();
    this.currentStock = request.currentStock();
    this.minimumStock = request.minimumStock();
  }

  @PrePersist
  public void prePersist() {
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();

    if (this.active == null) {
      this.active = true;
    }

    if (this.currentStock == null) {
      this.currentStock = 0;
    }

    if (this.minimumStock == null) {
      this.minimumStock = 0;
    }
  }

  @PreUpdate
  public void preUpdate() {
    this.updatedAt = LocalDateTime.now();
  }
  
}