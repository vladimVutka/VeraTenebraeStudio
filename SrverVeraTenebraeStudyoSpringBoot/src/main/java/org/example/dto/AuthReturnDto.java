package org.example.dto;

import org.example.userScript.UserEntity;

import javax.validation.constraints.NotBlank;

public class AuthReturnDto {
    @NotBlank
    private int id;

    @NotBlank
    private String firstname;

    @NotBlank
    private String middlename;

    @NotBlank
    private String lastname;

    @NotBlank
    private String username;

    @NotBlank
    private String email;

    @NotBlank
    private String role;

    @NotBlank
    private String imageUrl;

    @NotBlank
    private String password;

    public AuthReturnDto(UserEntity user){
        this.email = user.getEmail();
        this.firstname = user.getFirstname();
        this.imageUrl = user.getImageUrl();
        this.role = user.getRole();
        this.lastname = user.getLastname();
        this.username = user.getUsername();
        this.middlename = user.getMiddlename();
    }

    public int getId(){
        return id;
    }

    public @NotBlank String getEmail() {
        return email;
    }

    public @NotBlank String getImageUrl() {
        return imageUrl;
    }

    public @NotBlank String getFirstname() {
        return firstname;
    }

    public @NotBlank String getLastname() {
        return lastname;
    }

    public @NotBlank String getRole() {
        return role;
    }

    public @NotBlank String getUsername() {
        return username;
    }

}
