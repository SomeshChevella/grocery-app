package com.grocery.rest.webservices.grocery_app.user;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/users/{user_id}")
    public User getUserById(@PathVariable int user_id){
        return userService.getUserById(user_id);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @DeleteMapping("/users/{user_id}")
    public void deleteUserById(@PathVariable int id){
        userService.deleteUserById(id);
    }
}
