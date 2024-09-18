package com.grocery.rest.webservices.grocery_app.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        userRepository.save(user);
        return user;
    }

    public void deleteUserById(int userId){
        userRepository.deleteById(userId);
    }

    public User getUserById(int userId) {
        return userRepository.findById(userId).get();
    }
}
