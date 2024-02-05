package com.mustceng.dropshipping.service.unit;
import com.mustceng.dropshipping.DropshippingApplication;
import com.mustceng.dropshipping.dto.CategoryDTO;
import com.mustceng.dropshipping.entity.category.Category;
import com.mustceng.dropshipping.entity.category.CategoryCode;
import com.mustceng.dropshipping.exception.ApiException;
import com.mustceng.dropshipping.exception.ResponseCode;
import com.mustceng.dropshipping.exception.Severity;
import com.mustceng.dropshipping.repository.CategoryRepository;
import com.mustceng.dropshipping.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static com.sun.xml.internal.ws.policy.sourcemodel.wspolicy.XmlToken.Optional;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DropshippingApplication.class)
public class CategoryServiceImplTest {

//	@Mock
//	private CategoryRepository categoryRepository;

//	@InjectMocks
//	private  CategoryServiceImpl categoryService;

	@Autowired
	private CategoryServiceImpl categoryService;

	@Autowired
	private CategoryRepository categoryRepository;

	@Test
	void save() {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setCategoryCode(CategoryCode.SPORTS);
		categoryDTO.setName("Sneakers");
		categoryDTO.setActive(true);
		CategoryDTO categorySaved =categoryService.save(categoryDTO);
		assertEquals(CategoryCode.SPORTS, categorySaved.getCategoryCode());
		assertEquals(CategoryCode.BOOKS, categorySaved.getCategoryCode());

	}

	@Test
	void getCategoryById() {
		CategoryDTO categoryDTO = categoryService.getCategoryById(1L);
		assertEquals(CategoryCode.CD_DVD, categoryDTO.getCategoryCode());
		assertEquals("Sneakers", categoryDTO.getName());
	}

	@Test
	void delete() {
		Long id =4L;
		categoryService.delete(id);
		Category category = categoryRepository.findById(3L).orElseThrow(() -> new ApiException(ResponseCode.DATA_NOT_FOUND, Severity.ERROR, "Category"));
		assertEquals(id, category.getId());
	}

//	@BeforeEach
//	void setMockOutput() {
//		when(1L).thenReturn(1L);
//	}
//
//	@Test
//	void testGet() {
//		assertEquals(1L, java.util.Optional.ofNullable(categoryService.getCategoryById(1L).getId()));
//	}



}
