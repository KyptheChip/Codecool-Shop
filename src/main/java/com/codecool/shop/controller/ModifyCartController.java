package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.inMemory.CartDaoMem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "modifyCart",
        urlPatterns = {"/modifyCart"},
        initParams = {@WebInitParam(name = "id", value = ""),
                @WebInitParam(name = "amount", value = "")
        })
public class ModifyCartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String amount = request.getParameter("amount");
        if (id != null && !id.equals("")) {
            int productID = Integer.parseInt(id);

            CartDao cartDataStore = CartDaoMem.getInstance();


            int actAmount = Integer.parseInt(amount);

            cartDataStore.set(productID, actAmount);

        }
    }
}
