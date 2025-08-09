package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User update(Integer id, User updatedUser) {
        return userRepository.findById(id).map(existing -> {
            existing.setUsername(updatedUser.getUsername());
            existing.setFullname(updatedUser.getFullname());
            existing.setRole(updatedUser.getRole());
            existing.setPassword(updatedUser.getPassword());
            return userRepository.save(existing);
        }).orElseThrow(() -> new IllegalArgumentException("User not found: " + id));
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
