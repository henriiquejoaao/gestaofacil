package trigon.gestaofacil.service;

import trigon.gestaofacil.repository.SupplierRepository;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import trigon.gestaofacil.dto.SupplierRequestDTO;
import trigon.gestaofacil.dto.SupplierResponseDTO;
import trigon.gestaofacil.model.Supplier;

@Service
public class SupplierService {

  private final SupplierRepository repository;

  public SupplierService(SupplierRepository repository) {
    this.repository = repository;
  }

  public List<SupplierResponseDTO> getAll() {
    return repository.findAll().stream().map(SupplierResponseDTO::new).toList();
  }

  public List<SupplierResponseDTO> getActiveSuppliers() {
    return repository.findByActiveTrue().stream().map(SupplierResponseDTO::new).toList();
  }

  public SupplierResponseDTO getById(UUID id) {
    Supplier supplier = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Fornecedor não encontrado"));
    return new SupplierResponseDTO(supplier);
  }

  public SupplierResponseDTO save(SupplierRequestDTO data) {
    Supplier supplier = new Supplier();

    supplier.setName(data.name());
    supplier.setCnpj(data.cnpj());
    supplier.setPhone(data.phone());
    supplier.setEmail(data.email());
    supplier.setAddress(data.address());
    
    repository.save(supplier);

    return new SupplierResponseDTO(supplier);
  }

  public SupplierResponseDTO update(UUID id, SupplierRequestDTO data) {
    Supplier supplier = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Fornecedor não encontrado"));
    supplier.setName(data.name());
    supplier.setCnpj(data.cnpj());
    supplier.setPhone(data.phone());
    supplier.setEmail(data.email());
    supplier.setAddress(data.address());

    repository.save(supplier);

    return new SupplierResponseDTO(supplier);
  }

  public void delete(UUID id) {
    Supplier supplier = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Fornecedor não encontrado"));
    supplier.setActive(false);

    repository.save(supplier);
  }

  public SupplierResponseDTO activate(UUID id) {
    Supplier supplier = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Fornecedor não encontrado"));
    supplier.setActive(true);

    Supplier activatedSupplier = repository.save(supplier);

    return new SupplierResponseDTO(activatedSupplier);
  }

  public void hardDelete(UUID id) {
    Supplier supplier = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Fornecedor não encontrado"));

    repository.delete(supplier);
  }
}
