package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.UserOrderDao;
import com.codecool.shop.dao.implementation.UserOrderDaoMem;
import com.codecool.shop.model.User;
import com.codecool.shop.service.UserOrder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@WebServlet(urlPatterns = {"/checkout"})
    public class CheckoutController extends HttpServlet {
        public UUID userId;
        public int orderId;
        Random rand = new Random();
        TemplateEngine engine;
        WebContext context;
        HttpSession session;

        private void setData(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");
            session = req.getSession();
        }


    @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            setData(req, resp);
            this.engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            this.userId = UUID.randomUUID();
            this.orderId = rand.nextInt(1000);
            User user = (User) session.getAttribute("user");
            if( user == null ) {
                user = new User(userId);
                session.setAttribute("user", user);
            }
            this.context = new WebContext(req, resp, req.getServletContext());
            context.setVariable("order_id", orderId);
            engine.process("product/checkout.html", context, resp.getWriter());
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            setData(req, resp);
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String phoneNumber = req.getParameter("phoneNumber");
            String address = req.getParameter("address");
            String city = req.getParameter("inputCity");
            String zip = req.getParameter("inputZip");
            UserOrder userOrder = new UserOrder(orderId, userId, name, email, phoneNumber, address, city, zip);

            session.setAttribute("userOrder", userOrder);

            UserOrderDao userOrderDao = UserOrderDaoMem.getInstance();
            userOrderDao.add(userOrder);
            resp.sendRedirect(req.getContextPath() + "/payment?order_id=" + orderId);

        }

    }
