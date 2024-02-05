package com.mustceng.dropshipping.service.unit;

import com.mustceng.dropshipping.DropshippingApplication;
import com.mustceng.dropshipping.dto.ProductDTO;
import com.mustceng.dropshipping.repository.ProductRepository;
import com.mustceng.dropshipping.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DropshippingApplication.class)
public class ProductServiceImplTest {

//	@Mock
//	private ProductRepository productRepository;
//
//	@InjectMocks
//	private  ProductServiceImpl productService ;


	@Autowired
	private ProductServiceImpl productService;

	@Test
	void applyDiscount() {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(1L);
		ProductDTO productDTODiscounted = productService.applyDiscount(productDTO);
		assertEquals(null, productDTODiscounted.getNetPrice());

	}

	@Test
	void calculateTotalValueAllProducts() {
		BigDecimal totalValue= BigDecimal.ZERO;
		totalValue = productService.calculateTotalValueAllProducts();
		assertEquals(BigDecimal.ZERO, totalValue);
	}

//	@BeforeEach
//	void setMockOutput() {
//		when(1L).thenReturn(1L);
//	}
//
//	@Test
//	void testGet() {
//		assertEquals(1L, java.util.Optional.ofNullable(productService.getProductById(1L).getId()));
//	}



}
