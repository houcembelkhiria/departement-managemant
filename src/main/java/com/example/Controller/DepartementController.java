package com.example.Controller;


import com.example.Service.DepartementService;
import com.example.entity.Departement;
import com.example.DTO.DepartmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*
@RestController
public class DepartementController {
    private final DepartementService departementService;

    public DepartementController(DepartementService departementService) {
        this.departementService = departementService;
    }

    @PostMapping("/createNewDepartement")
    public Departement createDepartement(@RequestBody Departement departement) {
        return departementService.createDepartement(departement);
    }
    @GetMapping("/getAllDepartements")
    public List<Departement> getAllDepartements() {
        return departementService.getAllDepartements();
    }
    @GetMapping("/{departmentId}/users")
    public ResponseEntity<List<User>> getUsersInDepartment(@PathVariable Long departmentId) {
        List<User> users = departementService.getUsersInDepartment(departmentId);
        return ResponseEntity.ok(users);
    }

}
*/

@RestController
@RequestMapping("/departments")
public class DepartementController {
    @Autowired
    private DepartementService departmentService;

    @GetMapping("/")
    public List<DepartmentDTO> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public Departement getDepartmentById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id);
    }

    @PostMapping("/")
    public Departement createDepartment(@RequestBody Departement department) {
        return departmentService.createDepartment(department);
    }

    @PutMapping("/{id}")
    public Departement updateDepartment(@PathVariable Long id, @RequestBody Departement department) {
        department.setId_dep(id);
        return departmentService.updateDepartment(department);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }

    @PostMapping("/{departmentId}/users/{userId}")
    public void addUserToDepartment(@PathVariable Long departmentId, @PathVariable Long userId) {
        departmentService.addUserToDepartment(userId, departmentId);
    }

    @DeleteMapping("/{departmentId}/users/{userId}")
    public void removeUserFromDepartment(@PathVariable Long departmentId, @PathVariable Long userId) {
        departmentService.removeUserFromDepartment(userId, departmentId);
    }
}
