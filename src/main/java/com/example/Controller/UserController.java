package com.example.Controller;

import com.example.DTO.UserDTO;
import com.example.Repository.UserRepository;
import com.example.Service.UserService;
import com.example.entity.Departement;
import com.example.entity.User;
import com.example.entity.UserDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
/*
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/getAllusers")
    public List<User> getAllusers() {
        return userService.getAllusers();
    }

    @PostMapping("/signUp")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/{userId}/departments/{departmentId}")
    public ResponseEntity<String> addUserToDepartment(@PathVariable Long userId, @PathVariable Long departmentId) {
        userService.addUserToDep(userId, departmentId);
        return ResponseEntity.ok("User" + userId + " added to" + departmentId);
    }

    @GetMapping("/multiple-departments")
    public List<User> getUsersBelongingToMultipleDepartments() {
        return userService.getUsersBelongingToMultipleDepartments();
    }
}

*/

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }


    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId_user(id);
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
    @GetMapping("/department/{departmentId}")
    public Set <UserDepartment> getUsersByDepartment(@PathVariable Long departmentId) {
        return userService.getUsersByDepartment(departmentId);
    }
    @PostMapping("/{userId}/departments/{departmentId}")
    public String addUserToDepartment(@PathVariable Long userId, @PathVariable Long departmentId) {
        userService.addUserToDepartment(userId, departmentId);
        return "Adding user "+userService.getUserById(userId)+" to department "+ departmentId;
    }

    @DeleteMapping("/{userId}/departments/{departmentId}")
    public void removeUserFromDepartment(@PathVariable Long userId, @PathVariable Long departmentId) {
        userService.removeUserFromDepartment(userId, departmentId);
    }

    @GetMapping("/multipleDepartments")
    public List<User> getUsersInMultipleDepartments() {
        return userService.getUsersInMultipleDepartments();
    }
}
