package jjd.gestaofacil.dto;

import java.util.UUID;

import jjd.gestaofacil.model.Product;

public record ProductResponseDTO(UUID id, String name, Double price, String image) {

  public ProductResponseDTO(Product product) {
    this(product.getId(), product.getName(), product.getPrice(), product.getImage());
    
  }
}
