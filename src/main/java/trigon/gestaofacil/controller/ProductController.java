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
  public ResponseEntity<ProductResponseDTO> saveProduct(@RequestBody @Valid ProductRequestDTO dto) {
    ProductResponseDTO product = service.save(dto);

    return ResponseEntity.status(HttpStatus.CREATED).body(product);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid ProductRequestDTO dto) {
    ProductResponseDTO product = service.update(id, dto);

    return ResponseEntity.status(HttpStatus.OK).body(product);
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
    ProductResponseDTO product = service.activate(id);
    return ResponseEntity.status(HttpStatus.OK).body(product);
  }

}