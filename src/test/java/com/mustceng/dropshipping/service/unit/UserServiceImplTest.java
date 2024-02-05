package com.mustceng.dropshipping.service.unit;

import com.mustceng.dropshipping.DropshippingApplication;
import com.mustceng.dropshipping.dto.UserDTO;
import com.mustceng.dropshipping.repository.UserRepository;
import com.mustceng.dropshipping.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DropshippingApplication.class)
public class UserServiceImplTest {

//	@Mock
//	private UserRepository userRepository;
//
//	@InjectMocks
//	private  UserServiceImpl userService ;


	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserServiceImpl userService;

	@Test
	void save() {
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername("test");
		userDTO.setPassword(passwordEncoder.encode("12345678"));
		userDTO.setEmail("test@test.com");
		userDTO.setActive(true);
		UserDTO userSaved =userService.save(userDTO);
		assertEquals("test", userSaved.getUsername());
		assertEquals("test@gmail.com", userSaved.getEmail());

	}

	@Test
	void getUserById() {
		UserDTO userDTO = userService.getUserById(1L);
		assertEquals("test", userDTO.getUsername());
		assertEquals("mustceng@test.com", userDTO.getEmail());
	}


//	@BeforeEach
//	void setMockOutput() {
//		when(1L).thenReturn(1L);
//	}
//
//	@Test
//	void testGet() {
//		assertEquals(1L, java.util.Optional.ofNullable(userService.getUserById(1L).getId()));
//	}


}
