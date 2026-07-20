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

  private final SupplierRepository supplierRepository;

  public SupplierService(SupplierRepository supplierRepository) {
    this.supplierRepository = supplierRepository;
  }

  public List<SupplierResponseDTO> getAll() {
    return supplierRepository.findAll().stream().map(SupplierResponseDTO::new).toList();
  }

  public List<SupplierResponseDTO> getActiveSuppliers() {
    return supplierRepository.findByActiveTrue().stream().map(SupplierResponseDTO::new).toList();
  }

  public SupplierResponseDTO getById(UUID id) {
    Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Fornecedor não encontrado"));
    return new SupplierResponseDTO(supplier);
  }

  public SupplierResponseDTO save(SupplierRequestDTO request) {
    Supplier supplier = new Supplier();

    supplier.setName(request.name());
    supplier.setCnpj(request.cnpj());
    supplier.setPhone(request.phone());
    supplier.setEmail(request.email());
    supplier.setAddress(request.address());
    
    supplierRepository.save(supplier);

    return new SupplierResponseDTO(supplier);
  }

  public SupplierResponseDTO update(UUID id, SupplierRequestDTO request) {
    Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Fornecedor não encontrado."));
    supplier.setName(request.name());
    supplier.setCnpj(request.cnpj());
    supplier.setPhone(request.phone());
    supplier.setEmail(request.email());
    supplier.setAddress(request.address());

    supplierRepository.save(supplier);

    return new SupplierResponseDTO(supplier);
  }

  public void delete(UUID id) {
    Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Fornecedor não encontrado."));
    supplier.setActive(false);

    supplierRepository.save(supplier);
  }

  public SupplierResponseDTO activate(UUID id) {
    Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Fornecedor não encontrado."));
    supplier.setActive(true);

    Supplier activatedSupplier = supplierRepository.save(supplier);

    return new SupplierResponseDTO(activatedSupplier);
  }

  public void hardDelete(UUID id) {
    Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Fornecedor não encontrado."));

    supplierRepository.delete(supplier);
  }
  
}