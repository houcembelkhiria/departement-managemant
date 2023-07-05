package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "departements")
public class Departement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id_dep;


   //@Column(name = "dep_nom")
    private String Dep_nom;

    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private Set<UserDepartment> userDepartments = new HashSet<>();

    public Set<UserDepartment> getUserDepartments() {
        return userDepartments;
    }

    public void setUserDepartments(Set<UserDepartment> userDepartments) {
        this.userDepartments = userDepartments;
    }

    public long getId_dep() {
        return Id_dep;
    }

    public void setId_dep(long id_dep) {
        Id_dep = id_dep;
    }

    public String getDep_nom() {
        return Dep_nom;
    }

    public void setDep_nom(String dep_nom) {
        Dep_nom = dep_nom;
    }
}
