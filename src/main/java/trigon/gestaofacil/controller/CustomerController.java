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
import trigon.gestaofacil.dto.CustomerRequestDTO;
import trigon.gestaofacil.dto.CustomerResponseDTO;
import trigon.gestaofacil.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

  private final CustomerService service;

  public CustomerController(CustomerService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<CustomerResponseDTO>> getAll() {
    return ResponseEntity.ok(service.getAll());
  }

  @GetMapping("/active")
  public ResponseEntity<List<CustomerResponseDTO>> getActive() {
    return ResponseEntity.ok(service.getActive());
  }

  @GetMapping("/{id}")
  public ResponseEntity<CustomerResponseDTO> getById(@PathVariable UUID id) {
    return ResponseEntity.ok(service.getById(id));
  }

  @PostMapping
  public ResponseEntity<CustomerResponseDTO> save(@RequestBody @Valid CustomerRequestDTO request) {
    CustomerResponseDTO response = service.save(request);

    return ResponseEntity.status(201).body(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CustomerResponseDTO> update(@PathVariable UUID id, @RequestBody CustomerRequestDTO request) {
    CustomerResponseDTO response = service.update(id, request);

    return ResponseEntity.status(201).body(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable UUID id) {
    service.delete(id);

    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}/activate")
  public ResponseEntity<CustomerResponseDTO> activate(@PathVariable UUID id) {
    CustomerResponseDTO response = service.activate(id);

    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}/permanent")
  public ResponseEntity<Void> hardDelete(@PathVariable UUID id) {
    service.hardDelete(id);

    return ResponseEntity.noContent().build();
  }

}