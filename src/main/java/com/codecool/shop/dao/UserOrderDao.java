package com.codecool.shop.dao;

import com.codecool.shop.service.UserOrder;

import java.util.List;

public interface UserOrderDao {
    void  setPaymentData(String cName, String cNum, String expDate, String cvv);
    void add(UserOrder userOrder);
    UserOrder find(int id);
    void remove(int id);
    List<UserOrder> getAll();
}
