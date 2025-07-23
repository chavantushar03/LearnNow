package com.learnnow.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.learnnow.dao.UserEntityDao;
import com.learnnow.dto.LoginRequestDTO;
import com.learnnow.dto.LoginResponseDTO;
import com.learnnow.dto.RegisterRequestDTO;
import com.learnnow.dto.UserEntityResponseDTO;
import com.learnnow.exception.ApiException;
import com.learnnow.pojo.UserEntity;
import com.learnnow.security.JwtUtils;

@Service
public class AuthService {

    private final UserEntityDao userEntityDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final ModelMapper mapper;
    
    @Autowired
<<<<<<< HEAD
    public AuthService(UserEntityDao userEntityDao, PasswordEncoder passwordEncoder, JwtUtils jwtUtils, ModelMapper mapper) {
=======
    public AuthService(UserEntityDao userEntityDao, PasswordEncoder passwordEncoder,
                       JwtUtils jwtUtils, ModelMapper mapper) {
>>>>>>> abhishek
        this.userEntityDao = userEntityDao;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.mapper = mapper;
    }

    public LoginResponseDTO login(LoginRequestDTO request) {
<<<<<<< HEAD
        UserEntity user = userEntityDao.findByEmailAndIsActiveTrue(request.getEmail()).orElseThrow(() -> new BadCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }

        String token = jwtUtils.generateToken(user);
        UserEntityResponseDTO userDto = mapper.map(user, UserEntityResponseDTO.class);
        LoginResponseDTO response = new LoginResponseDTO();
        response.setToken(token);
        response.setUser(userDto);
        response.setMessage("Login successful");
=======
        UserEntity user = userEntityDao.findByEmailAndIsActiveTrue(request.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Invalid email"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        String token = jwtUtils.generateToken(user);
       
        UserEntityResponseDTO userDto = new UserEntityResponseDTO(
                user.getId(),
                user.getCreatedOn(),
                user.getUpdatedOn(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getDob(),
                user.getUserRole().name()
            );

            LoginResponseDTO response = new LoginResponseDTO();
            response.setToken(token);
            response.setUser(userDto);
            response.setMessage("Login successful");
>>>>>>> abhishek

        return response;
    }
    
    public LoginResponseDTO register(RegisterRequestDTO requestDto) {
<<<<<<< HEAD
=======
      // Check for existing user
>>>>>>> abhishek
      if (userEntityDao.findByEmailAndIsActiveTrue(requestDto.getEmail()).isPresent()) {
          throw new ApiException("User already exists");
      }

      UserEntity entity = mapper.map(requestDto, UserEntity.class);
      entity.setPassword(passwordEncoder.encode(entity.getPassword()));
      UserEntity savedUser = userEntityDao.save(entity);
      String token = jwtUtils.generateToken(savedUser);

<<<<<<< HEAD
=======
      System.out.println("======================>" + savedUser);
>>>>>>> abhishek
      UserEntityResponseDTO userDto = mapper.map(savedUser, UserEntityResponseDTO.class);

      LoginResponseDTO response = new LoginResponseDTO();
      response.setToken(token);
      response.setUser(userDto);
      response.setMessage("Registration successful");
      return response;
<<<<<<< HEAD
=======
    	
    	
    	
//        // Check for existing user
//        if (userEntityDao.findByEmailAndIsActiveTrue(requestDto.getEmail()).isPresent()) {
//            throw new RuntimeException("User already exists");
//        }
//        // Create and save new user
//        UserEntity user = new UserEntity();
//        user.setFirstName(requestDto.getFirstName());
//        user.setLastName(requestDto.getLastName());
//        user.setEmail(requestDto.getEmail());
//        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
//        user.setDob(requestDto.getDob());
//        user.setUserRole(UserRole.valueOf(requestDto.getUserRole().toUpperCase()));
//        System.out.println("Before Save: " + user.getCreatedOn());
//        userEntityDao.save(user);
//        System.out.println("After Save: " + user.getCreatedOn());
//
//        // Prepare user DTO
//        UserEntityResponseDTO userDto = new UserEntityResponseDTO(
//            user.getId(),
//            user.getCreatedOn(),
//            user.getUpdatedOn(),
//            user.getFirstName(),
//            user.getLastName(),
//            user.getEmail(),
//            user.getDob(),
//            user.getUserRole().name()
//        );
//
//        // Generate token
//        String token = jwtUtils.generateToken(user);
//
//        // Prepare response
//        LoginResponseDTO response = new LoginResponseDTO();
//        response.setToken(token);
//        response.setUser(userDto);
//        response.setMessage("Registration successful");
//
//        return response;
>>>>>>> abhishek
    }


}


<<<<<<< HEAD
=======
//package com.learnnow.service;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.learnnow.dao.UserEntityDao;
//import com.learnnow.dto.LoginRequestDTO;
//import com.learnnow.dto.LoginResponseDTO;
//import com.learnnow.dto.RegisterRequestDTO;
//import com.learnnow.dto.UserEntityResponseDTO;
//import com.learnnow.exception.ApiException;
//import com.learnnow.pojo.UserEntity;
//import com.learnnow.security.JwtUtils;
//
//import lombok.AllArgsConstructor;
//
//@Service
//@Transactional
//@AllArgsConstructor
//public class AuthService {
//    private UserEntityDao userEntityDao;
//	private ModelMapper mapper;
//    private PasswordEncoder passwordEncoder;
//    private JwtUtils jwtUtils;
//    
//
//    public LoginResponseDTO login(LoginRequestDTO request) {
//    	  UserEntity user = userEntityDao.findByEmailAndIsActiveTrue(request.getEmail())
//                  .orElseThrow(() -> new BadCredentialsException("Invalid email"));
//          if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
//              throw new BadCredentialsException("Invalid password");
//          }
//          String token = jwtUtils.generateToken(user);
//          UserEntityResponseDTO userDto = mapper.map(user, UserEntityResponseDTO.class);
//          LoginResponseDTO response = new LoginResponseDTO();
//          response.setToken(token);
//          response.setUser(userDto);
//          response.setMessage("Login successful");
//          return response;
//    }
//    
//    public LoginResponseDTO register(RegisterRequestDTO requestDto) {
//        // Check for existing user
//        if (userEntityDao.findByEmailAndIsActiveTrue(requestDto.getEmail()).isPresent()) {
//            throw new ApiException("User already exists");
//        }
//
//        UserEntity entity = mapper.map(requestDto, UserEntity.class);
//        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
//        UserEntity savedUser = userEntityDao.save(entity);
//        String token = jwtUtils.generateToken(savedUser);
//
//        UserEntityResponseDTO userDto = mapper.map(savedUser, UserEntityResponseDTO.class);
//
//        LoginResponseDTO response = new LoginResponseDTO();
//        response.setToken(token);
//        response.setUser(userDto);
//        response.setMessage("Registration successful");
//        return response;
//    }
//
//
//}
>>>>>>> abhishek
