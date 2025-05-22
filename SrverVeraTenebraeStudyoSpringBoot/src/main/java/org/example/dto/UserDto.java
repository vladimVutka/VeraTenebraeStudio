package org.example.dto;

import javax.validation.constraints.NotBlank;

public class UserDto {
    private Long id;

    private String firstname;
    private String middlename;
    private String lastname;

    @NotBlank
    private String username;
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private String imageUrl;



    public String getFirstname() { return firstname; }
    public String getMiddlename() { return middlename; }
    public String getLastname() { return lastname; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getImageUrl() {return imageUrl;}

    public void setFirstname(String firstname) { this.firstname = firstname; }
    public void setMiddlename(String middlename) {this.middlename = middlename;}
    public void setLastname(String lastname) { this.lastname = lastname;}
    public void setUsername(String username) { this.username = username; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }

    public void setImageUrl(String imageUrl) {this.imageUrl = imageUrl;}
}
