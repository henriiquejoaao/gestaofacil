package trigon.gestaofacil.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import trigon.gestaofacil.dto.CustomerRequestDTO;
import trigon.gestaofacil.dto.CustomerResponseDTO;
import trigon.gestaofacil.model.Customer;
import trigon.gestaofacil.repository.CustomerRepository;

@Service
public class CustomerService {

  private final CustomerRepository customerRepository;

  public CustomerService(CustomerRepository customerRespoitory) {
    this.customerRepository = customerRespoitory;
  }

  public List<CustomerResponseDTO> getAll() {
    return customerRepository.findAll().stream().map(CustomerResponseDTO::new).toList();
  }

  public List<CustomerResponseDTO> getActive() {
    return customerRepository.findByActiveTrue().stream().map(CustomerResponseDTO::new).toList();
  }

  public CustomerResponseDTO getById(UUID id) {
    Customer customer = findCustomer(id);

    return new CustomerResponseDTO(customer);
  }

  public CustomerResponseDTO save(CustomerRequestDTO request) {
    Customer customer = customerRepository.save(new Customer(request));
    
    return new CustomerResponseDTO(customer);
  }

  public CustomerResponseDTO update(UUID id, CustomerRequestDTO request) {
    Customer customer = findCustomer(id);

    customer.updateFromDTO(request);

    Customer updatedCustomer = customerRepository.save(customer);

    return new CustomerResponseDTO(updatedCustomer);
  }

  public void delete(UUID id) {
    Customer customer = findCustomer(id);

    customer.setActive(false);

    customerRepository.save(customer);
  }

  public CustomerResponseDTO activate(UUID id) {
    Customer customer = findCustomer(id);

    customer.setActive(true);

    Customer activatedCustomer = customerRepository.save(customer);

    return new CustomerResponseDTO(activatedCustomer);
  }

  public void hardDelete(UUID id) {
    customerRepository.deleteById(id);
  }

  public Customer findCustomer(UUID id) {
    return customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado."));
  }

}