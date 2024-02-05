package com.mustceng.dropshipping.entity.product;

import com.mustceng.dropshipping.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface ProductMapper {

	ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

	ProductDTO to(Product product );

	Product from(ProductDTO productDTO);

}
