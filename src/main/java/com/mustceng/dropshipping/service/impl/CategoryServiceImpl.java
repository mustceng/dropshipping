package com.mustceng.dropshipping.service.impl;

import com.mustceng.dropshipping.dto.CategoryDTO;
import com.mustceng.dropshipping.entity.category.Category;
import com.mustceng.dropshipping.entity.category.CategoryMapper;
import com.mustceng.dropshipping.exception.ApiException;
import com.mustceng.dropshipping.exception.ResponseCode;
import com.mustceng.dropshipping.exception.Severity;
import com.mustceng.dropshipping.repository.CategoryRepository;
import com.mustceng.dropshipping.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;


	@Override
	public CategoryDTO getCategoryById(Long id){
		return CategoryMapper.INSTANCE.to(categoryRepository.findById(id).orElseThrow(() -> new ApiException(ResponseCode.DATA_NOT_FOUND, Severity.ERROR, "Category")));
	}

	@Override
	public Page<CategoryDTO> getAllCategories(Pageable pageable){
		Page<Category> categoryPage = categoryRepository.findAll(pageable);
		List<CategoryDTO> categoryDTOList = categoryPage.stream().map(CategoryMapper.INSTANCE::to).collect(Collectors.toList());
		PageImpl<CategoryDTO> categoryDTOPage = new PageImpl<>(categoryDTOList, pageable, categoryPage.getTotalElements());
		return categoryDTOPage;
	}

	@Override
	@Transactional
	public CategoryDTO save(CategoryDTO categoryDTO){
		Category categorySaved = categoryRepository.save(CategoryMapper.INSTANCE.from(categoryDTO));
		return CategoryMapper.INSTANCE.to(categorySaved);
	}

	@Override
	@Transactional
	public CategoryDTO update(Long id,CategoryDTO categoryDTO){
		Category updateCategory = categoryRepository.findById(id).orElseThrow(() -> new ApiException(ResponseCode.DATA_NOT_FOUND, Severity.ERROR, "Category"));

		updateCategory.setName(categoryDTO.getName());
		updateCategory.setCategoryCode(categoryDTO.getCategoryCode());

		Category categoryUpdated = categoryRepository.save(updateCategory);
		return CategoryMapper.INSTANCE.to(categoryUpdated);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		categoryRepository.deleteById(id);
	}

}
