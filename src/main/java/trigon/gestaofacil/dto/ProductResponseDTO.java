package trigon.gestaofacil.dto;

import java.util.UUID;

import trigon.gestaofacil.model.Product;

public record ProductResponseDTO(UUID id, String name, Double price, String image) {

  public ProductResponseDTO(Product product) {
    this(product.getId(), product.getName(), product.getPrice(), product.getImage());
  }

}