package trigon.gestaofacil.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import trigon.gestaofacil.dto.StockMovementRequestDTO;
import trigon.gestaofacil.dto.StockMovementResponseDTO;
import trigon.gestaofacil.service.StockMovementService;

@RestController
@RequestMapping("/stock-movements")
class StockMovementController {

  private final StockMovementService service;

  public StockMovementController(StockMovementService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<StockMovementResponseDTO>> getAll() {
    return ResponseEntity.ok(service.getAll());
  }

  @GetMapping("/latest")
  public ResponseEntity<List<StockMovementResponseDTO>> getLatest() {
    return ResponseEntity.ok(service.getLatest());
  }

  @GetMapping("/today")
  public ResponseEntity<List<StockMovementResponseDTO>> getToday() {
    return ResponseEntity.ok(service.getToday());
  }

  @GetMapping("/today/count")
  public ResponseEntity<Long> countToday() {
    return ResponseEntity.ok(service.countToday());
  }

  @GetMapping("/product/{productId}")
  public ResponseEntity<List<StockMovementResponseDTO>> getByProduct(@PathVariable UUID productId) {
    return ResponseEntity.ok(service.getByProduct(productId));
  }

  @PostMapping("/in")
  public ResponseEntity<StockMovementResponseDTO> registerIn(@RequestBody @Valid StockMovementRequestDTO data) {
    StockMovementResponseDTO movement = service.registerIn(data);
    return ResponseEntity.status(201).body(movement);
  }

  @PostMapping("/out")
  public ResponseEntity<StockMovementResponseDTO> registerOut(@RequestBody @Valid StockMovementRequestDTO data) {
    StockMovementResponseDTO movement = service.registerOut(data);
    return ResponseEntity.status(201).body(movement);
  }
}