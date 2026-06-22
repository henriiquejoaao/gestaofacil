package trigon.gestaofacil.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import trigon.gestaofacil.dto.StockMovementResponseDTO;
import trigon.gestaofacil.repository.StockMovementRepository;

@Service
public class StockMovementService {

  private final StockMovementRepository repository;

  public StockMovementService(StockMovementRepository repository) {
    this.repository = repository;
  }

  public List<StockMovementResponseDTO> getAll() {
    return repository.findAll().stream().map(StockMovementResponseDTO::new).toList();
  }

  public List<StockMovementResponseDTO> getLatest() {
    return repository.findTop10ByOrderByCreatedAtDesc().stream().map(StockMovementResponseDTO::new).toList();
  }

  public List<StockMovementResponseDTO> getByProduct(UUID productId) {
    return repository.findByProductIdOrderByCreatedAtDesc(productId).stream().map(StockMovementResponseDTO::new).toList();
  }

  public List<StockMovementResponseDTO> getToday() {
    LocalDate today = LocalDate.now();

    LocalDateTime start = today.atStartOfDay();
    LocalDateTime end = today.plusDays(1).atStartOfDay();

    return repository.findByCreatedAtBetweenOrderByCreatedAtDesc(start, end).stream().map(StockMovementResponseDTO::new).toList();
  }

  public long countToday() {
    LocalDate today = LocalDate.now();

    LocalDateTime start = today.atStartOfDay();
    LocalDateTime end = today.plusDays(1).atStartOfDay();

    return repository.countByCreatedAtBetween(start, end);
  }
}
