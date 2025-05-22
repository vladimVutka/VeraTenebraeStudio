package DataFiles;

import java.sql.Struct;

public class UserEntity {
    private final Long userId;
    private final  String firstname;
    private final String middlename;
    private final String lastname;
    private final String username;
    private final String email;
    private final String role;
    private final String imageurl;



    public UserEntity(String username, String email, String firstname, String middlename, String lastname, Long userId, String role, String imageurl){
        this.email = email;
        this.lastname = lastname;
        this.username = username;
        this.firstname = firstname;
        this.middlename = middlename;
        this.userId = userId;
        this.imageurl = imageurl;
        this.role = role;
    }


    public String getName()
    {
        return firstname;
    }

    public String getUsername()
    {
        return username;
    }
    public Long getId()
        {
        return userId;
    }

    public String getImageurl() {return imageurl;}


}
