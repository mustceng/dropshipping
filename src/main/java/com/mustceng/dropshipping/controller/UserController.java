package com.mustceng.dropshipping.controller;


import com.mustceng.dropshipping.dto.UserDTO;
import com.mustceng.dropshipping.response.ApiResponse;
import com.mustceng.dropshipping.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;


	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/{id}")
	public ApiResponse<UserDTO> getUserById(@PathVariable("id") Long id){
		return ApiResponse.of(userService.getUserById(id));
	}

	@GetMapping("/all")
	public ApiResponse<Page<UserDTO>> getAllPaged(Pageable pageable){
		return ApiResponse.of(userService.getAllUsers(pageable));
	}

	@PostMapping
	public ApiResponse<UserDTO> save(@RequestBody UserDTO userDTO){
		return ApiResponse.of(userService.save(userDTO));
	}

	@PutMapping("/{id}")
	public ApiResponse<UserDTO> update(@PathVariable("id") Long id,@RequestBody UserDTO userDTO){
		return ApiResponse.of(userService.update(id,userDTO));
	}

	@DeleteMapping("/{id}")
	public ApiResponse delete(@PathVariable("id") Long id){
		userService.delete(id);
		return ApiResponse.success();
	}

}
