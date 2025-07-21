package com.learnnow.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learnnow.dao.UserEntityDao;
import com.learnnow.dto.UserEntityResponseDTO;
import com.learnnow.exception.ResourceNotFoundException;
import com.learnnow.pojo.UserEntity;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class UserEntityService {

		public final UserEntityDao userEntityDao;
		private ModelMapper mapper;
    	
	    public ResponseEntity<?> getUserById(Long id) {
	        UserEntity user = userEntityDao.findByIdAndIsActiveTrue(id)
	                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found"));

	        UserEntityResponseDTO responseDto = mapper.map(user, UserEntityResponseDTO.class);
	        return ResponseEntity.ok(responseDto);
	    }

	    public ResponseEntity<?> getAllActiveUsers() {
	        List<UserEntity> users = userEntityDao.findByIsActiveTrue();
	        if (users.isEmpty()) {
	            throw new ResourceNotFoundException("No active users found");
	        }
	        List<UserEntityResponseDTO> dtoList = users.stream()
	                .map(user -> mapper.map(user, UserEntityResponseDTO.class))
	                .collect(Collectors.toList());

	        return ResponseEntity.ok(dtoList);
	    }

	    public ResponseEntity<?> softDeleteUser(Long id) {
	        UserEntity user = userEntityDao.findByIdAndIsActiveTrue(id)
	                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found"));
	        user.setActive(false);
	        userEntityDao.save(user);
	        return ResponseEntity.ok("User deleted successfully");
	    }
}
