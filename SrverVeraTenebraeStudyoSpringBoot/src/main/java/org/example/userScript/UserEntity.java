package org.example.userScript;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstname;

    @Column
    private String middlename;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private String role;

    @Column
    private String imageUrl;

    public String getMiddlename(){return middlename;}
    public String getUsername(){return username;}
    public String getLastname(){return lastname;}
    public String getFirstname(){return firstname;}
    public String getEmail(){return email;}
    public String getRole(){return role;}
    public String getPassword(){return password;}
    public String getImageUrl() {return imageUrl;}

    public void setUsername(String username){this.username = username;}

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
