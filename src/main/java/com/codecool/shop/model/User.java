package com.codecool.shop.model;

import java.util.UUID;

public class User{
    private UUID id;

    public User(UUID id){
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
