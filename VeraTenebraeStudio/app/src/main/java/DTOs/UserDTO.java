package DTOs;

import DataFiles.UserEntity;

public class UserDTO {
    private Long id;
    private String firstname;
    private String middlename;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private String role;
    private String imageUrl;

    public UserDTO(String email, String password, String firstname, String middlename, String lastname, String username, String role, String imageUrl) {
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.middlename = middlename;
        this.role = role;
        this.imageUrl = imageUrl;
    }

    public UserDTO(String email, String password, String firstname, String middlename, String lastname, String username) {
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.middlename = middlename;
    }
    public UserDTO(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UserEntity toUserEntity() {
        return new UserEntity(this.username,
                this.email,
                this.firstname,
                this.middlename,
                this.lastname,
                this.id,
                this.role,
                this.imageUrl);
    }

    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getMiddlename() { return middlename; }
    public String getPassword() {return password; }
    public String getRole() { return  role; }
    public String getImageurl() { return  imageUrl; }

    public void setFirstname(String firstname) { this.firstname = firstname; }
    public void setLastname(String lastname) { this.lastname = lastname; }
    public void setUsername(String username) { this.username = username; }
    public void setEmail(String email) { this.email = email; }
    public void setMiddlename(String middlename) { this.middlename = middlename; }
    public void setPassword(String password) {this.password = password; }
    public void setRole(String role) { this.role = role; }
    public void setImageUrl(String imageurl) { this.imageUrl = imageurl; }
}