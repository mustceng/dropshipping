package com.mustceng.dropshipping.service.impl;

import com.mustceng.dropshipping.dto.UserDTO;
import com.mustceng.dropshipping.entity.user.User;
import com.mustceng.dropshipping.entity.user.UserMapper;
import com.mustceng.dropshipping.exception.ApiException;
import com.mustceng.dropshipping.exception.ResponseCode;
import com.mustceng.dropshipping.exception.Severity;
import com.mustceng.dropshipping.repository.UserRepository;
import com.mustceng.dropshipping.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;


	@Override
	public UserDTO getUserById(Long id){
		return UserMapper.INSTANCE.to(userRepository.findById(id).orElseThrow(() -> new ApiException(ResponseCode.DATA_NOT_FOUND, Severity.ERROR, "User")));
	}

	@Override
	public User findByIdAndActive(Long id,Boolean active){
		return userRepository.findByIdAndActive(id,active).orElseThrow(() -> new ApiException(ResponseCode.DATA_NOT_FOUND, Severity.ERROR, "User"));
	}

	public User getUserByUsername(String username) {
		User user = userRepository.findByUsername(username).orElseThrow(()-> new ApiException(ResponseCode.DATA_NOT_FOUND,"User"));
		return user;
	}

	@Override
	public Page<UserDTO> getAllUsers(Pageable pageable){
		Page<User> userPage = userRepository.findAll(pageable);
		List<UserDTO> userDTOList = userPage.stream().map(UserMapper.INSTANCE::to).collect(Collectors.toList());
		PageImpl<UserDTO> userDTOPage = new PageImpl<>(userDTOList, pageable, userPage.getTotalElements());
		return userDTOPage;
	}

	@Override
	@Transactional
	public UserDTO save(UserDTO userDTO){
		User userSaved = userRepository.save(UserMapper.INSTANCE.from(userDTO));
		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		return UserMapper.INSTANCE.to(userSaved);
	}

	@Override
	@Transactional
	public UserDTO update(Long id,UserDTO userDTO){
		User updateUser = userRepository.findById(id).orElseThrow(() -> new ApiException(ResponseCode.DATA_NOT_FOUND, Severity.ERROR, "User"));

		updateUser.setUsername(userDTO.getUsername());
		updateUser.setEmail(userDTO.getEmail());

		User userUpdated = userRepository.save(updateUser);
		return UserMapper.INSTANCE.to(userUpdated);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

}
