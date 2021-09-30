package com.codecool.shop.model;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class User{
    private UUID tempID;
    private int id;
    private String name;
    private String email;
    private String password;

    public User(int id, String name, String email, String password) throws NoSuchAlgorithmException {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(UUID id) {
        this.tempID = id;
    }

    public User(String name, String email, String password) throws NoSuchAlgorithmException {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UUID getTempID() {
        return tempID;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws NoSuchAlgorithmException {
        this.password = password;
    }

    public int getID() {
        return this.id;
    }
}
