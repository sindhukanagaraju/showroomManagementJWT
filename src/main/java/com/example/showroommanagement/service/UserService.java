package com.example.showroommanagement.service;

import com.example.showroommanagement.entity.User;
import com.example.showroommanagement.exception.BadRequestServiceAlertException;
import com.example.showroommanagement.exception.UserExistsException;
import com.example.showroommanagement.exception.UserNotExistException;
import com.example.showroommanagement.repository.UserRepository;
import com.example.showroommanagement.util.Constant;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional

    public User createSignUp(final User user) {
        User existingUser = this.userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            throw new UserExistsException(Constant.EXIST_MAIL);
        }
        return this.userRepository.save(user);
    }

    @Transactional
    public User createSignIn(final User user) {
        return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword()).orElseThrow(() -> new UserNotExistException("Invalid email or password. Please try again."));
    }

    public User retrieveUserById(final Integer id) {
        return this.userRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
    }

    public List<User> retrieveUser() {
        return this.userRepository.findAll();
    }

    @Transactional
    public User updateUserById(final User user, final Integer id) {
        final User existingUser = this.userRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
        if (user.getName() != null) {
            existingUser.setName(user.getName());
        }
        if (user.getId() != null) {
            existingUser.setId(user.getId());
        }
        if (user.getUserType() != null) {
            existingUser.setUserType(user.getUserType());
        }
        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }
        if (user.getPassword() != null) {
            existingUser.setPassword(user.getPassword());
        }
        if (user.getCreatedAt() != null) {
            existingUser.setCreatedAt(user.getCreatedAt());
        }

        return this.userRepository.save(existingUser);
    }

    public String removeUserById(final Integer id) {
        if (this.userRepository.existsById(id)) {
            this.userRepository.deleteById(id);
            return Constant.DELETE;
        } else {
            throw new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST);
        }
    }
}
