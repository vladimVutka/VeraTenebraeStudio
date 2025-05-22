package org.example.dto;

import javax.validation.constraints.NotBlank;

public class AuthDto {
    @NotBlank
    private String login;
    @NotBlank
    private String password;

    public String getLogin(){
        return login;
    }

    public String getPassword(){
        return password;
    }
}
