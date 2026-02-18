package jjd.gestaofacil.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jjd.gestaofacil.repository.ProductRepository;
import jjd.gestaofacil.dto.ProductRequestDTO;
import jjd.gestaofacil.dto.ProductResponseDTO;
import jjd.gestaofacil.model.Product;

@Service
public class ProductService {

  @Autowired
  private ProductRepository repository;

  public List<ProductResponseDTO> getAll() {
    return repository.findAll().stream().map(ProductResponseDTO::new).toList();
  }

  public void save(ProductRequestDTO dto) {
    repository.save(new Product(dto));
  }

  public void delete(UUID id) {
    repository.deleteById(id);
  }

  public ProductResponseDTO getById(UUID id) {
    Product product = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
    return new ProductResponseDTO(product);
  }

}
