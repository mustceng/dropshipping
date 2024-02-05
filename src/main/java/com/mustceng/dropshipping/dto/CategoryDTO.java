package com.mustceng.dropshipping.dto;

import com.mustceng.dropshipping.entity.category.Category;
import com.mustceng.dropshipping.entity.category.CategoryCode;
import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO  {
	private Long id;
	private Boolean active;
	private String name;
	private CategoryCode categoryCode;
	private Category parentCategory;

	public void setCategoryCodeMap(Map<String, String> categoryCodeMap) {
		if (categoryCodeMap != null) {
			this.categoryCode = CategoryCode.fromValue(categoryCodeMap.get("value"));
		}
	}

	public Map<String, String> getCategoryCodeMap() {
		if (categoryCode == null) return null;
		Map<String, String> categoryCodeMap = new HashMap<>();
		categoryCodeMap.put("name", categoryCode.name());
		categoryCodeMap.put("value", categoryCode.getValue());
		return categoryCodeMap;
	}

}
