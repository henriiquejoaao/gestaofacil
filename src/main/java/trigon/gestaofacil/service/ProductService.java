package trigon.gestaofacil.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import trigon.gestaofacil.dto.ProductRequestDTO;
import trigon.gestaofacil.dto.ProductResponseDTO;
import trigon.gestaofacil.model.Product;
import trigon.gestaofacil.repository.ProductRepository;

@Service
public class ProductService {

  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<ProductResponseDTO> getAll() {
    return productRepository.findAll().stream().map(ProductResponseDTO::new).toList();
  }

  public ProductResponseDTO save(ProductRequestDTO request) {
    Product product = productRepository.save(new Product(request));

    return new ProductResponseDTO(product);
  }

  public ProductResponseDTO update(UUID id, ProductRequestDTO request) {
    Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado."));

    product.setName(request.name());
    product.setDescription(request.description());
    product.setBarcode(request.barcode());
    product.setCostPrice(request.costPrice());
    product.setSalePrice(request.salePrice());
    product.setCurrentStock(request.currentStock());
    product.setMinimumStock(request.minimumStock());

    Product updatedProduct = productRepository.save(product);

    return new ProductResponseDTO(updatedProduct);
  }

  public void delete(UUID id) {
    Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado."));

    product.setActive(false);
    productRepository.save(product);
  }

  public void hardDelete(UUID id) {
    Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado."));

    productRepository.delete(product);
  }

  public ProductResponseDTO getById(UUID id) {
    Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado."));

    return new ProductResponseDTO(product);
  }

  public ProductResponseDTO activate(UUID id) {
    Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado."));
    product.setActive(true);
    
    Product activatedProduct = productRepository.save(product);

    return new ProductResponseDTO(activatedProduct);
  }

  public ProductResponseDTO getByBarcode(String barcode) {
    Product product = productRepository.findByBarcode(barcode).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado."));

    return new ProductResponseDTO(product);
  }

  public List<ProductResponseDTO> getLowStockProducts() {
    return productRepository.findLowStockProducts().stream().map(ProductResponseDTO::new).toList();
  }

  public List<ProductResponseDTO> getOutOfStockProducts() {
    return productRepository.findOutOfStockProducts().stream().map(ProductResponseDTO::new).toList();
  }
  
}