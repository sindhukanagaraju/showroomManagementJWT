package com.showroommanagement.service;

import com.showroommanagement.dto.JwtAuthenticationResponseDTO;
import com.showroommanagement.dto.RefreshTokenRequestDTO;
import com.showroommanagement.dto.SignInDTO;
import com.showroommanagement.dto.SignUpDTO;
import com.showroommanagement.entity.User;
import com.showroommanagement.exception.BadRequestServiceAlertException;
import com.showroommanagement.repository.UserRepository;
import com.showroommanagement.util.Constant;
import com.showroommanagement.util.UserType;
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
            throw new BadRequestServiceAlertException(Constant.EXIST_ACCOUNT);
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
            throw new BadRequestServiceAlertException(Constant.EXIST_ACCOUNT);
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
            throw new BadRequestServiceAlertException(Constant.EXIST_ACCOUNT);
        }
        return this.userRepository.save(user);
    }

    public JwtAuthenticationResponseDTO signIn(SignInDTO signInDTO) {
        final User user = this.userRepository.findByEmail(signInDTO.getEmail()).orElseThrow(() -> new RuntimeException(Constant.INCORRECT_EMAIL));
        if (!encoder.matches(signInDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException(Constant.INCORRECT_PASSWORD);
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInDTO.getEmail(), signInDTO.getPassword()));
        final String jwt = jwtService.generateToken(user);
        final String refreshToken = jwtService.generateRefreshToken(user);
        final JwtAuthenticationResponseDTO jwtAuthResp = new JwtAuthenticationResponseDTO();
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
        throw new BadRequestServiceAlertException(Constant.INVALID_TOKEN);
    }

    public User retrieveUserById(final int id) {
        return this.userRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.USER_NOT_FOUND));
    }

    public List<User> retrieveUser() {
        return this.userRepository.findAll();
    }

    public User deleteById(final int id) {
        final User user = this.userRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
        this.userRepository.deleteById(id);
        return user;
    }
}
