package com.mustceng.dropshipping.entity.product;

import com.mustceng.dropshipping.entity.base.BaseEntity;
import com.mustceng.dropshipping.entity.category.Category;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product extends BaseEntity {

	@Column(name = "title", length = 100)
	private String title;

	@Column(name = "brand", length = 100)
	private String brand;

	@Column(name = "image_url", length = 400)
	private String imageUrl;

	@Column(name = "price", precision = 18, scale = 2)
	private BigDecimal price;

	@Column(name = "net_price", precision = 18, scale = 2)
	private BigDecimal netPrice;

	@Column(name = "rating")
	private Integer rating;

	@Column(name = "amount")
	private Integer amount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

}
