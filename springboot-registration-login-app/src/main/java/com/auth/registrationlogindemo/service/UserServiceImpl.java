package com.auth.registrationlogindemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.registrationlogindemo.dto.UserDto;
import com.auth.registrationlogindemo.entity.Role;
import com.auth.registrationlogindemo.entity.User;
import com.auth.registrationlogindemo.repository.RoleRepository;
import com.auth.registrationlogindemo.repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
    private RoleRepository roleRepository;

	@Autowired
    private UserRepository userRepository;
    
	@Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        
        Role role = roleRepository.findByName("ROLE_ADMIN");
        
        if(role == null){
            role = checkRoleExist();
        }
        
        user.setRoles(Arrays.asList(role));
        
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
    	
        return userRepository.findByEmail(email);
        
    }

    @Override
    public List<UserDto> findAllUsers() {
    	
        List<User> users = userRepository.findAll();
        
        return users.stream().map((user) -> convertEntityToDto(user))
                .collect(Collectors.toList());
        
    }

    private UserDto convertEntityToDto(User user){
    	
        UserDto userDto = new UserDto();
        String[] name = user.getName().split(" ");
        userDto.setFirstName(name[0]);
        userDto.setLastName(name[1]);
        userDto.setEmail(user.getEmail());
        
        return userDto;
        
    }

    private Role checkRoleExist() {
    	
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        
        return roleRepository.save(role);
    }
}
