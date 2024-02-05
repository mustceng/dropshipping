package com.mustceng.dropshipping.entity.category;

import com.mustceng.dropshipping.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "category")
public class Category extends BaseEntity {
	@Column(name = "name", length = 100)
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name="category_code")
	private CategoryCode categoryCode;

	@ManyToOne
	@JoinColumn(name = "parent_category_id")
	private Category parentCategory;


}
