package trigon.gestaofacil.controller;

import java.util.List;
import java.util.UUID;

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
import trigon.gestaofacil.dto.SupplierRequestDTO;
import trigon.gestaofacil.dto.SupplierResponseDTO;
import trigon.gestaofacil.service.SupplierService;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {
  
  private final SupplierService service;

  public SupplierController(SupplierService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<SupplierResponseDTO>>getAll() {
    return ResponseEntity.ok(service.getAll());
  }

  @GetMapping("/active")
  public ResponseEntity<List<SupplierResponseDTO>> getActive() {
    return ResponseEntity.ok(service.getActiveSuppliers());
  }

  @GetMapping("/{id}")
  public ResponseEntity<SupplierResponseDTO> getById(@PathVariable UUID id) {
    return ResponseEntity.ok(service.getById(id));
  }

  @PostMapping
  public ResponseEntity<SupplierResponseDTO> save(@RequestBody @Valid SupplierRequestDTO dto) {
    SupplierResponseDTO supplier = service.save(dto);

    return ResponseEntity.status(201).body(supplier);
  }

  @PutMapping("/{id}")
  public ResponseEntity<SupplierResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid SupplierRequestDTO dto) {
    SupplierResponseDTO supplier = service.update(id, dto);

    return ResponseEntity.ok(supplier);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable UUID id) {
    service.delete(id);

    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}/activate")
  public ResponseEntity<SupplierResponseDTO> activate(@PathVariable UUID id) {
    SupplierResponseDTO supplier = service.activate(id);

    return ResponseEntity.ok(supplier);
  }

  @DeleteMapping("/{id}/permanent")
  public ResponseEntity<Void> hardDelete(@PathVariable UUID id) {
    service.hardDelete(id);
    
    return ResponseEntity.noContent().build();
  }
}
