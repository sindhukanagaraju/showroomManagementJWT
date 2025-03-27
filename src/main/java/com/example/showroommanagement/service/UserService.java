package com.example.showroommanagement.service;

import com.example.showroommanagement.dto.JwtAuthenticationResponseDTO;
import com.example.showroommanagement.dto.RefreshTokenRequestDTO;
import com.example.showroommanagement.dto.SignInDTO;
import com.example.showroommanagement.dto.SignUpDTO;
import com.example.showroommanagement.entity.User;
import com.example.showroommanagement.exception.BadRequestServiceAlertException;
import com.example.showroommanagement.repository.UserRepository;
import com.example.showroommanagement.util.Constant;
import com.example.showroommanagement.util.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User adminCreate(final SignUpDTO signUpDTO) {
        final User user = new User();
        user.setName(signUpDTO.getName());
        user.setEmail(signUpDTO.getEmail());
        user.setPassword(encoder.encode(signUpDTO.getPassword()));
        user.setUserType(UserType.ADMIN);
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new BadRequestServiceAlertException("Account Already exists");
        }
        return this.userRepository.save(user);
    }

    public User employeeCreate(final SignUpDTO signUpDTO) {
        final User user = new User();
        user.setName(signUpDTO.getName());
        user.setEmail(signUpDTO.getEmail());
        user.setPassword(encoder.encode(signUpDTO.getPassword()));
        user.setUserType(UserType.EMPLOYEE);
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new BadRequestServiceAlertException("Account Already exists");
        }
        return this.userRepository.save(user);
    }

    public User customerCreate(final SignUpDTO signUpDTO) {
        final User user = new User();
        user.setName(signUpDTO.getName());
        user.setEmail(signUpDTO.getEmail());
        user.setPassword(encoder.encode(signUpDTO.getPassword()));
        user.setUserType(UserType.CUSTOMER);
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new BadRequestServiceAlertException("Account Already exists");
        }
        return this.userRepository.save(user);
    }

    public JwtAuthenticationResponseDTO signIn(SignInDTO signInDTO) {
        final User user = this.userRepository.findByEmail(signInDTO.getEmail()).orElseThrow(() -> new RuntimeException("Incorrect EmailId"));
        if (!encoder.matches(signInDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Incorrect Password");
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInDTO.getEmail(), signInDTO.getPassword()));
        final String jwt = jwtService.generateToken(user);
        final String refreshToken=jwtService.generateRefreshToken(user);
        final JwtAuthenticationResponseDTO jwtAuthResp=new JwtAuthenticationResponseDTO();
        jwtAuthResp.setToken(jwt);
        jwtAuthResp.setRefreshToken(refreshToken);
        return jwtAuthResp;
    }
    public JwtAuthenticationResponseDTO refreshToken(final RefreshTokenRequestDTO refreshTokenRequestDTO) {
        String userEmail = jwtService.extractUserName(refreshTokenRequestDTO.getToken());

        User user = userRepository.findByEmail(userEmail).orElseThrow();

        if (jwtService.isTokenValid(refreshTokenRequestDTO.getToken(), user)) {
            var jwt = jwtService.generateToken(user);

            JwtAuthenticationResponseDTO jwtAuthenticationResponse = new JwtAuthenticationResponseDTO();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequestDTO.getToken());
            return jwtAuthenticationResponse;
        }
        throw new RuntimeException("Invalid Refresh Token");
    }

    public User getById(final int id) {
        return this.userRepository.findById(id).orElseThrow(() -> new RuntimeException(Constant.USER_NOT_FOUND));
    }

    public List<User> findByAllAndId() {
        return this.userRepository.findAll();
    }

    public String deleteById(final int id) {
        final User user = this.userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found for this id : " + id));
        this.userRepository.delete(user);
        return Constant.REMOVE;
    }

}
