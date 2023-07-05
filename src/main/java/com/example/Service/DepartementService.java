package com.example.Service;

import com.example.Repository.DepartementRepository;
import com.example.Repository.UserDepartmentRepository;
import com.example.Repository.UserRepository;
import com.example.entity.Departement;
import com.example.DTO.DepartmentDTO;
import com.example.entity.User;
import com.example.entity.UserDepartment;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/*
@Service
public class DepartementService {

    @Autowired
    private final DepartementRepository departementRepository;

    public DepartementService(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }

    public Departement createDepartement(Departement departement) {
        departementRepository.save(departement);
        return departementRepository.save(departement);
    }
    public List<Departement> getAllDepartements (){
        return (List<Departement>) departementRepository.findAll();
    }
    public List<User> getUsersInDepartment(Long departmentId) {
        Departement department = departementRepository.findById(departmentId).orElse(null);
        return new ArrayList<>(department.getUsers());
    }

}*/
@Service
@Transactional
public class DepartementService {
    @Autowired
    private DepartementRepository departmentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDepartmentRepository userDepartmentRepository;

    public List<DepartmentDTO> getAllDepartments() {
        List<Departement> departments = departmentRepository.findAll();
        return departments.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private DepartmentDTO convertToDto(Departement department) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(department.getId_dep());
        dto.setName(department.getDep_nom());
        return dto;
    }
    public Departement getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    public Departement createDepartment(Departement department) {
        return departmentRepository.save(department);
    }

    public Departement updateDepartment(Departement department) {
        return departmentRepository.save(department);
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
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

}
