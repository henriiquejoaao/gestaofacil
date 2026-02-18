package jjd.gestaofacil.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import jjd.gestaofacil.model.Product;

public interface ProductRepository extends JpaRepository<Product, UUID> {}