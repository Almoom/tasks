package com.moc.tasks.service;

import com.moc.tasks.model.UserEntity;

public interface UserService {
    UserEntity create(UserEntity user);
    UserEntity getCurrentUser();
}
