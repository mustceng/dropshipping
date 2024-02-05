package com.mustceng.dropshipping.service;

import com.mustceng.dropshipping.dto.ProductDTO;
import com.mustceng.dropshipping.response.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface ProductService {
	ProductDTO getProductById(Long id);

	Page<ProductDTO> getProductByCategoryId(Long id, Pageable pageable);

	Page<ProductDTO> getAllProducts(Pageable pageable);

	ProductDTO save(ProductDTO productDTO);

	ProductDTO applyDiscount(ProductDTO productDTO);

    BigDecimal calculateTotalValueAllProducts();

	ProductDTO update(Long id,ProductDTO productDTO);

	void delete(Long id);

}
