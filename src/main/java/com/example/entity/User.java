package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;

    //@Column(name = "nom")
    private String Nom;

  //  @Column(name = "prenom")
    private String Prenom;

   // @Column(name = "email")
    private String Email;
   // @Column(name = "mdp")
    private String mdp;
   // @Column(name = "datenaissance")
    private Date dateNaissance;


    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<UserDepartment> userDepartments = new HashSet<>();

    public Set<UserDepartment> getUserDepartments() {
        return userDepartments;
    }

    public void setUserDepartments(Set<UserDepartment> userDepartments) {
        this.userDepartments = userDepartments;
    }


    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
}
