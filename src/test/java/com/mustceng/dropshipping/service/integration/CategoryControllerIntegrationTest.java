package com.mustceng.dropshipping.service.integration;

import com.mustceng.dropshipping.dto.CategoryDTO;
import com.mustceng.dropshipping.entity.category.CategoryCode;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerIntegrationTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getTestCategoryById() throws Exception {
		testCategoryById(1L);
	}


	@Test
	public void getTestSave() throws Exception {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setCategoryCode(CategoryCode.SPORTS);
		categoryDTO.setName("Adidas");
		categoryDTO.setActive(true);
		testSave(categoryDTO);
	}

	private void testCategoryById(Long id) throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/category/{id}",1L)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();


		String resultDOW = result.getResponse().getContentAsString();
		JSONObject jsonCategory=new JSONObject(resultDOW);
		assertNotNull(resultDOW);
		assertEquals(id, Long.valueOf(jsonCategory.getString("id")));
	}

	private void testSave(CategoryDTO categoryDTO) throws Exception {

		ObjectWriter ow = (ObjectWriter) new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(categoryDTO);
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/category")
						.content(json)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		String resultDOW = result.getResponse().getContentAsString();
		assertNotNull(resultDOW);

		JSONObject jsonCategory=new JSONObject(resultDOW);
		assertEquals("Adidas", jsonCategory.getString("name"));
		assertEquals("Kinetix", jsonCategory.getString("name"));
	}
}
