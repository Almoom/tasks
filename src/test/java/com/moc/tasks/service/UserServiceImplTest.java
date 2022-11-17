package com.moc.tasks.service;

import com.moc.tasks.model.UserEntity;
import com.moc.tasks.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void create() {
        UserEntity user = new UserEntity();
        user.setPassword("Qwerty123!");
        String encodedPassword = "jf2w0jisdlfnweijdf0qjiedsolkfmwepofdjmsd";

        Mockito.doReturn(encodedPassword).when(passwordEncoder)
                .encode(user.getPassword());

        userService.saveUser(user);
        Mockito.verify(userRepository).save(user);
        Assertions.assertEquals(encodedPassword, user.getPassword());
    }

}