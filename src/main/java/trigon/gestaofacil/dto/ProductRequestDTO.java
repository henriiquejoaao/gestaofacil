package trigon.gestaofacil.dto;

import trigon.gestaofacil.model.Product;

public record ProductRequestDTO(String name, Double price, String image) {

  public ProductRequestDTO(Product product) {
    this(product.getName(), product.getPrice(), product.getImage());
  }

}
