package com.mustceng.dropshipping.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	private Long id;
	private Boolean active;
	private String title;
	private String brand;
	private String imageUrl;
	private BigDecimal price;
	private BigDecimal netPrice;
	private Integer rating;
	private Integer amount;
	private CategoryDTO category;
}
