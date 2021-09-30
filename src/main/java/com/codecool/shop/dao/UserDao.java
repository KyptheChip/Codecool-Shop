package com.codecool.shop.dao;

import com.codecool.shop.model.User;

public interface UserDao {
    void add(User user);
    User find(int id);
    User findByEmailOrName(String email);

    void updateName(String name, String email);
    void updatePassword(String password, String email);

    void remove(int id);
    void removeByEmail(String email);
}
