package com.mustceng.dropshipping.entity.productdiscount;

import com.mustceng.dropshipping.entity.base.BaseEntity;
import com.mustceng.dropshipping.entity.product.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "product_discount")
public class ProductDiscount extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@Column(name = "discount_ratio")
	private BigDecimal discountRatio;

	@Column(name = "discount_non_expired")
	private boolean discountNonExpired = true;

	@Column(name = "start_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;

	@Column(name = "end_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;


}
