package com.mustceng.dropshipping.service;

import com.mustceng.dropshipping.dto.UserDTO;
import com.mustceng.dropshipping.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

	UserDTO getUserById(Long id);

	User findByIdAndActive(Long id, Boolean active);

	User getUserByUsername(String username);

	Page<UserDTO> getAllUsers(Pageable pageable);

	UserDTO save(UserDTO userDTO);

	UserDTO update(Long id,UserDTO userDTO);

	void delete(Long id);


}
