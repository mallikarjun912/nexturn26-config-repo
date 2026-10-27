package cqrs.query.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cqrs.query.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
