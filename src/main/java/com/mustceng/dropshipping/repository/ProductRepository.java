package com.mustceng.dropshipping.repository;

import com.mustceng.dropshipping.entity.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Page<Product> findAll(Pageable pageable);

	Page<Product> findByCategoryId(Long id,Pageable pageable);

	@Query("SELECT SUM(p.price*p.amount) FROM Product p")
	BigDecimal totalValueProducts();

}
