package com.auth.registrationlogindemo.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.auth.registrationlogindemo.dto.UserDto;
import com.auth.registrationlogindemo.entity.Role;
import com.auth.registrationlogindemo.entity.User;
import com.auth.registrationlogindemo.repository.RoleRepository;
import com.auth.registrationlogindemo.repository.UserRepository;
import com.auth.registrationlogindemo.service.UserServiceImpl;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

	private static final String UUID = "9fgc#ds%$fcuk";
	
	private static final Long ID = 1L;

	@InjectMocks
	UserServiceImpl userServiceImpl;

	@Mock
	private UserRepository userRepository;

	@Mock
	private RoleRepository roleRepository;

	@Mock
	private PasswordEncoder passwordEncoder;
	
	@Mock
	private com.auth.registrationlogindemo.dto.UserDto userDto;
	
	@Mock
	private Role role;
	
	@Mock
	private User user;

	@BeforeEach
	public void setUp() {

		userDto = new UserDto();
		userDto.setFirstName("Rose");
		userDto.setLastName("Dawson");
		userDto.setEmail("rose.dawson@love.com");
		userDto.setId(ID);
		userDto.setPassword("Rose@6969");
		
		user=new User();
		user.setName("Rose Dawson");
		user.setEmail("rose.dawson@love.com");
		user.setId(ID);
		user.setPassword(UUID);

	}
	
	@Test
	public void testSaveUser() {
		Mockito.when(passwordEncoder.encode(ArgumentMatchers.anyString())).thenReturn(UUID);
		Mockito.when(roleRepository.findByName(ArgumentMatchers.anyString())).thenReturn(null);
		Mockito.when(userRepository.save(ArgumentMatchers.any())).thenReturn(user);
		userServiceImpl.saveUser(userDto);
		assertTrue(true);
		
	}
	
	@Test
	public void testFindByEmail() {
		Mockito.when(userRepository.findByEmail(ArgumentMatchers.anyString())).thenReturn(user);
		assertNotNull(userServiceImpl.findByEmail(UUID));
	}
	
	@Test
	public void testFindAllUsers() {
		Mockito.when(userRepository.findAll()).thenReturn(Arrays.asList(user));
		assertNotNull(userServiceImpl.findAllUsers());
	}
}
