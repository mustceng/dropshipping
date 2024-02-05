package com.mustceng.dropshipping.entity.user;

import com.mustceng.dropshipping.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	UserDTO to(User user );

	User from(UserDTO userDTO);

}
