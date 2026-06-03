package trigon.gestaofacil.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import trigon.gestaofacil.dto.ProductRequestDTO;
import trigon.gestaofacil.dto.ProductResponseDTO;
import trigon.gestaofacil.model.Product;
import trigon.gestaofacil.repository.ProductRepository;

@Service
public class ProductService {

  @Autowired
  private ProductRepository repository;

  public List<ProductResponseDTO> getAll() {
    return repository.findAll().stream().map(ProductResponseDTO::new).toList();
  }

  public ProductResponseDTO save(ProductRequestDTO data) {
    Product product = repository.save(new Product(data));

    return new ProductResponseDTO(product);
  }

  public ProductResponseDTO update(UUID id, ProductRequestDTO data) {
    Product product = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

    product.setName(data.name());
    product.setDescription(data.description());
    product.setBarcode(data.barcode());
    product.setCostPrice(data.costPrice());
    product.setSalePrice(data.salePrice());
    product.setCurrentStock(data.currentStock());
    product.setMinimumStock(data.minimumStock());

    Product updatedProduct = repository.save(product);

    return new ProductResponseDTO(updatedProduct);
  }

  public void delete(UUID id) {
    Product product = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

    product.setActive(false);
    repository.save(product);
  }

  public void hardDelete(UUID id) {
    Product product = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

    repository.delete(product);
  }

  public ProductResponseDTO getById(UUID id) {
    Product product = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

    return new ProductResponseDTO(product);
  }

  public ProductResponseDTO activate(UUID id) {
    Product product = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
    product.setActive(true);
    
    Product activatedProduct = repository.save(product);

    return new ProductResponseDTO(activatedProduct);
  }

}