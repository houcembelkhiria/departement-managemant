package com.example.Service;

import com.example.DTO.UserDTO;
import com.example.Repository.DepartementRepository;
import com.example.Repository.UserDepartmentRepository;
import com.example.entity.Departement;
import com.example.entity.User;
import com.example.Repository.UserRepository;
import com.example.entity.UserDepartment;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/*
@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final DepartementRepository departementRepository;

    public UserService(UserRepository userRepository , DepartementRepository departementRepository) {
        this.userRepository = userRepository;
        this.departementRepository = departementRepository;
    }


    public List<User> getAllusers (){
        return (List<User>) userRepository.findAll();
    }

    public User registerUser(User user) {
        userRepository.save(user);
    return userRepository.save(user);
    }

    public void addUserToDep(Long userId, Long departmentId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Departement department = departementRepository.findById(departmentId).orElseThrow(() -> new EntityNotFoundException("Department not found"));

        user.getDepartments().add(department);
        department.getUsers().add(user);

        userRepository.save(user);
        departementRepository.save(department);
    }

    public List<User> getUsersBelongingToMultipleDepartments() {
        return userRepository.findUsersBelongingToMultipleDepartments();
    }
}
*/

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DepartementRepository departmentRepository;
    @Autowired
    private UserDepartmentRepository userDepartmentRepository;
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private UserDTO convertToDto(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId_user());
        dto.setName(user.getNom());
        return dto;
    }
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    public Set <UserDepartment> getUsersByDepartment(Long departmentId) {
        Departement department = departmentRepository.findById(departmentId).orElse(null);
            return department.getUserDepartments();

    }
    public void addUserToDepartment(Long userId, Long departmentId) {
        User user = userRepository.findById(userId).orElse(null);
        Departement department = departmentRepository.findById(departmentId).orElse(null);
        if (user != null && department != null) {
            UserDepartment userDepartment = new UserDepartment();
            userDepartment.setUser(user);
            userDepartment.setDepartment(department);
            userDepartmentRepository.save(userDepartment);
        }
    }

    public void removeUserFromDepartment(Long userId, Long departmentId) {
        User user = userRepository.findById(userId).orElse(null);
        Departement department = departmentRepository.findById(departmentId).orElse(null);
        if (user != null && department != null) {
            UserDepartment userDepartment = userDepartmentRepository.findByUserAndDepartment(user, department);
            if (userDepartment != null) {
                userDepartmentRepository.delete(userDepartment);
            }
        }
    }

    public List<User> getUsersInMultipleDepartments() {
        return userRepository.findUsersInMultipleDepartments();
    }

}
