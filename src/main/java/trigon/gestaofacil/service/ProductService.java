package trigon.gestaofacil.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import trigon.gestaofacil.repository.ProductRepository;
import trigon.gestaofacil.dto.ProductRequestDTO;
import trigon.gestaofacil.dto.ProductResponseDTO;
import trigon.gestaofacil.model.Product;

@Service
public class ProductService {

  @Autowired
  private ProductRepository repository;

  public List<ProductResponseDTO> getAll() {
    return repository.findAll().stream().map(ProductResponseDTO::new).toList();
  }

  public void save(ProductRequestDTO data) {
    repository.save(new Product(data));
  }

  public Product update(UUID id, ProductRequestDTO data) {
    Product product = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
    product.setName(data.name());
    product.setPrice(data.price());
    product.setImage(data.image());

    return repository.save(product);
  }

  public void delete(UUID id) {
    repository.deleteById(id);
  }

  public ProductResponseDTO getById(UUID id) {
    Product product = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
    return new ProductResponseDTO(product);
  }

}