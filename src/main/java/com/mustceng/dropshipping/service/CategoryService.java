package com.mustceng.dropshipping.service;

import com.mustceng.dropshipping.dto.CategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

	CategoryDTO getCategoryById(Long id);

	Page<CategoryDTO> getAllCategories(Pageable pageable);

	CategoryDTO save(CategoryDTO categoryDTO);

	CategoryDTO update(Long id,CategoryDTO categoryDTO);

	void delete(Long id);


}
