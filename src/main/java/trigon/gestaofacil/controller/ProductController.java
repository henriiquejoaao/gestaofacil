package trigon.gestaofacil.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import trigon.gestaofacil.dto.ProductRequestDTO;
import trigon.gestaofacil.dto.ProductResponseDTO;
import trigon.gestaofacil.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

  private final ProductService service;

  public ProductController(ProductService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<ProductResponseDTO>> getAll() {
    return ResponseEntity.ok(service.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductResponseDTO> getById(@PathVariable UUID id) {
    return ResponseEntity.ok(service.getById(id));
  }

  @PostMapping
  public ResponseEntity<ProductResponseDTO> saveProduct(@RequestBody @Valid ProductRequestDTO request) {
    ProductResponseDTO response = service.save(request);

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid ProductRequestDTO request) {
    ProductResponseDTO response = service.update(id, request);

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable UUID id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}/permanent")
  public ResponseEntity<Void> hardDelete(@PathVariable UUID id) {
    service.hardDelete(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}/activate")
  public ResponseEntity<ProductResponseDTO> activate(@PathVariable UUID id) {
    ProductResponseDTO response = service.activate(id);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @GetMapping("/barcode/{barcode}")
  public ResponseEntity<ProductResponseDTO> getByBarcode(@PathVariable String barcode) {
    return ResponseEntity.ok().body(service.getByBarcode(barcode));
  }

  @GetMapping("/low-stock")
  public ResponseEntity<List<ProductResponseDTO>> getLowStockProducts() {
    return ResponseEntity.ok().body(service.getLowStockProducts());
  }

  @GetMapping("/out-of-stock")
  public ResponseEntity<List<ProductResponseDTO>> getOutOfStock() {
    return ResponseEntity.ok().body(service.getOutOfStockProducts());
  }
  
}