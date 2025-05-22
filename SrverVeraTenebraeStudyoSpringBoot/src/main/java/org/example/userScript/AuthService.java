package org.example.userScript;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.AuthDto;
import org.example.dto.AuthReturnDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
public class AuthService {
        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;

        public AuthService(
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
                this.userRepository = userRepository;
                this.passwordEncoder = passwordEncoder;
        }

        @Transactional
        public UserEntity register(UserEntity user) {
                if (userRepository.existsByUsername(user.getUsername())) {
                        throw new RuntimeException("Данный логин занят");
                }
                if (userRepository.existsByEmail(user.getEmail())) {
                        throw new RuntimeException("Эта почта уже используется");
                }

                user.setPassword(passwordEncoder.encode(user.getPassword()));
                return userRepository.save(user);
        }
        public AuthReturnDto login(AuthDto request) {

                UserEntity userEntity = userRepository.findByUsernameOrEmail(request.getLogin())
                        .orElseThrow(() -> {
                                return new RuntimeException("Неверный логин");
                        });


                if(!passwordEncoder.matches(request.getPassword(), userEntity.getPassword())) {
                        throw new RuntimeException("Неверный логин или пароль");
                }

                return new AuthReturnDto(userEntity);
        }
}