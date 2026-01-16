package com.example.junit.service;

import com.example.junit.entities.User;
import com.example.junit.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User nor found"));
    }

    public User create (User user) {
        user.setId(null);
        return userRepository.save(user);
    }

    public User update (Long id, User user) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User nor found");
        }
        user.setId(id);
        return userRepository.save(user);
    }

}
