package com.tuananhdo.service;

import com.tuananhdo.entity.User;

import java.util.List;

public interface UserService {

    List<User> listUser();

    void addUser(User user);

    void deleteUser(Long id);

    User getUserById(Long id);
}
