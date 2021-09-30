package com.codecool.shop.dao;

import com.codecool.shop.model.CartProduct;
import com.codecool.shop.model.Product;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface CartDao {
    void add(int id);
    void set(int id, int amount);
    void remove(int id);
    void decreaseProductQuantity(int id);
    List<CartProduct> getAll() throws SQLException;
    BigDecimal getTotalPrice() throws SQLException;

    void clearShoppingCart();
}
