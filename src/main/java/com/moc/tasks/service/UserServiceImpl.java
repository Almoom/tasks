package com.moc.tasks.service;

import com.moc.tasks.model.UserEntity;
import com.moc.tasks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity create(UserEntity user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }



    @Override
    public UserEntity getCurrentUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        org.springframework.security.core.userdetails.User principal =
//                (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        return userRepository.findByLogin("dd")
                .orElseThrow(() -> new IllegalArgumentException("user not found"));
    }

}
