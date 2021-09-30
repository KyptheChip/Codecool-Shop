package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.inDatabase.DatabaseManager;
import com.codecool.shop.dao.inDatabase.ProductDaoJDBC;
import com.codecool.shop.dao.inMemory.CartDaoMem;
import com.codecool.shop.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.FileHandler;

@WebServlet(name = "addToCart", urlPatterns = {"/cart"})
public class AddToCartController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            DataSource dataSource = new DatabaseManager().connect();

            String id = request.getParameter("id");
            if (id != null) {
                int productID = Integer.parseInt(id);

                CartDao cartDataStore = CartDaoMem.getInstance();
                ProductDao productDataStore = new ProductDaoJDBC(dataSource);
                Product actProduct = productDataStore.find(productID);

                if (actProduct != null) {
                    cartDataStore.add(productID);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
