package com.ecw.repository;

import com.ecw.model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    void updateUser(User user);
    List<User> listUsers();
    User getUserById(Long id);
    void remoteUser(Long id);
}
