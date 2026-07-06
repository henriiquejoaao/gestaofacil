package trigon.gestaofacil.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.transaction.Transactional;
import trigon.gestaofacil.dto.StockMovementRequestDTO;
import trigon.gestaofacil.dto.StockMovementResponseDTO;
import trigon.gestaofacil.enums.StockMovementReason;
import trigon.gestaofacil.enums.StockMovementType;
import trigon.gestaofacil.model.Product;
import trigon.gestaofacil.model.StockMovement;
import trigon.gestaofacil.repository.ProductRepository;
import trigon.gestaofacil.repository.StockMovementRepository;

@Service
public class StockMovementService {

  private final StockMovementRepository repository;
  private final ProductRepository productRepository;


  public StockMovementService(StockMovementRepository repository, ProductRepository productRepository) {
    this.repository = repository;
    this.productRepository = productRepository;
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

  @Transactional
  public StockMovementResponseDTO registerIn(StockMovementRequestDTO data) {
    Product product = findProduct(data.productId());
    validateInReason(data.reason());

    int currentStock = product.getCurrentStock() == 0 ? 0 : product.getCurrentStock();

    StockMovement movement = new StockMovement(product,
    StockMovementType.IN,
    data.quantity(),
    data.reason());

    product.setCurrentStock(currentStock + data.quantity());

    StockMovement savedMovement = repository.save(movement);

    return new StockMovementResponseDTO(savedMovement);
  }

  private Product findProduct(UUID id) {
    return productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
  }

  private void validateInReason(StockMovementReason reason) {
    if (reason == StockMovementReason.SALE || reason == StockMovementReason.LOSS) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Esse motivo não é permitido para entrada de estoque");
    }
  }

  private void validateOutReason(StockMovementReason reason) {
    if (reason == StockMovementReason.PURCHASE || reason == StockMovementReason.RETURN || reason == StockMovementReason.INITIAL_STOCK) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Esse motivo não é permitido para saída de estoque");
    }
  }

  @Transactional
  public StockMovementResponseDTO registerOut(StockMovementRequestDTO data) {
    Product product = findProduct(data.productId());
    validateOutReason(data.reason());

    int currentStock = product.getCurrentStock() == null ? 0 : product.getCurrentStock();

    if (currentStock < data.quantity()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Estoque insuficiente para realizar a saída");
    }

    product.setCurrentStock(currentStock - data.quantity());

    StockMovement movement = new StockMovement(
      product,
      StockMovementType.OUT,
      data.quantity(),
      data.reason()
    );

    StockMovement savedStockMovement = repository.save(movement);

    return new StockMovementResponseDTO(savedStockMovement);
  }
}