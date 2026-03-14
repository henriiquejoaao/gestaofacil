package trigon.gestaofacil.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import trigon.gestaofacil.dto.ProductRequestDTO;
import trigon.gestaofacil.dto.ProductResponseDTO;
import trigon.gestaofacil.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired
  private ProductService service;

  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping
  public List<ProductResponseDTO> getAll() {
    return service.getAll();
  }

  @GetMapping("/{id}")
  public ProductResponseDTO getById(@PathVariable UUID id) {
    return service.getById(id);
  }

  @PostMapping
  public void saveProduct(@RequestBody ProductRequestDTO dto) {
    service.save(dto);
  }

  @PutMapping("/{id}")
  public void update(@PathVariable UUID id, @RequestBody ProductRequestDTO dto) {
    service.update(id, dto);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable UUID id) {
    service.delete(id);
  }

}