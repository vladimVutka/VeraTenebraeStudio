    package org.example.userScript;


    import org.example.dto.AuthReturnDto;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;

    import java.util.Optional;

    public interface UserRepository extends JpaRepository<UserEntity, Long>
    {
        UserEntity findByEmail(String email);

        UserEntity findById(long id);

        UserEntity findByUsername(String username);

        boolean existsByUsername(String username);

        boolean existsByEmail(String email);



        @Query("SELECT u FROM UserEntity u WHERE u.username = :login OR u.email = :login")
        Optional<UserEntity> findByUsernameOrEmail(@Param("login") String login);
    }
