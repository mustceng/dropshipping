package com.mustceng.dropshipping.entity.category;

import com.mustceng.dropshipping.dto.CategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CategoryMapper {

	CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

	CategoryDTO to(Category category );

	Category from(CategoryDTO categoryDTO);

}
