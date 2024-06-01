package com.example.restapiservertask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long empNo) {
        return userRepository.findById(empNo);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long empNo, User userDetails) {
        User user = userRepository.findById(empNo).orElseThrow(() -> new RuntimeException("User not found"));
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setGender(userDetails.getGender());
        return userRepository.save(user);
    }

    public void deleteUser(Long empNo) {
        userRepository.deleteById(empNo);
    }
}