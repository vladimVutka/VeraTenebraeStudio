package DTOs;

public class LoginDTO
{
    private String login;
    private String password;

    public LoginDTO(String login, String password){
        this.login = login;
        this.password = password;
    }

    public String getUsername() { return login; }
    public String getPassword() { return password; }
}
