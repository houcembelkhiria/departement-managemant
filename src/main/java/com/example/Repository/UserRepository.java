package com.example.Repository;

import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
  /*  @Query("SELECT u FROM User u WHERE SIZE(u.departements) > 1")
    List<User> findUsersBelongingToMultipleDepartments();*/

    @Query("SELECT u FROM User u WHERE u.id IN (SELECT ud.user.id FROM UserDepartment ud GROUP BY ud.user HAVING COUNT(ud.user) > 1)")
    List<User> findUsersInMultipleDepartments();
}
