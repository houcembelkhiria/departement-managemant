package com.example.Repository;
import com.example.entity.Departement;
import com.example.entity.User;
import com.example.entity.UserDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDepartmentRepository extends JpaRepository<UserDepartment, Long> {
    UserDepartment findByUserAndDepartment(User user, Departement department);
}
