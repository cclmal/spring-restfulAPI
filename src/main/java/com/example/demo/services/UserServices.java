package com.example.demo.services;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.models.User;
import com.example.demo.services.interfaces.UserService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServices implements UserService {
    private final UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));
    }

    @Override
    public List<User> getUsersFiltered(String prefix) {
        return userRepository.findByNameStartingWithIgnoreCase(prefix);
    }

    @Override
    public User updateUserById(Integer id, User userDetails) {
        // Buscar si el usuario existe
        return userRepository.findById(id)
            .map(user -> {
                // Actualizar los campos
                user.setName(userDetails.getName());
                user.setEmail(userDetails.getEmail());
                User updatedUser = userRepository.save(user);
                return updatedUser;
            })
            .orElseThrow(() -> new ResourceNotFoundException("User", id));
    }

    @Override
    public void deleteUserById(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User", id);
        }
        userRepository.deleteById(id);
    }
}
