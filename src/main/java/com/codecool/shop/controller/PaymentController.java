package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.inMemory.UserOrderDaoMem;
import com.codecool.shop.service.UserOrder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@WebServlet(urlPatterns = {"/payment"})
public class PaymentController extends HttpServlet {
    TemplateEngine engine;
    WebContext context;
    HttpSession session;

    private void setData(HttpServletRequest req, HttpServletResponse resp) {
        session = req.getSession();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setData(req, resp);
        this.engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        this.context = new WebContext(req, resp, req.getServletContext());
        //Total sum of order here maybe

        engine.process("product/payment.html", context, resp.getWriter());
    }

    @Override
    protected  void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setData(req, resp);
        UserOrder user = (UserOrder) session.getAttribute("userOrder");
        int id = user.getOrderId();
        String cardName = req.getParameter("cardName");
        String cardNumber = req.getParameter("cardNumber");
        String expDate= req.getParameter("expDate");
        String cvv = req.getParameter("cvv");

        UserOrder userOrder = UserOrderDaoMem.getInstance().find(id);
        userOrder.setPaymentData(cardName, cardNumber, expDate, cvv);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("_yyyy_MM_dd");
        String fileName = "order_" + id + dtf.format(LocalDate.now());
        FileOutputStream fileOutputStream = new FileOutputStream(fileName + ".json");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(userOrder);
        objectOutputStream.flush();
        objectOutputStream.close();

        resp.sendRedirect(req.getContextPath() + "/confirmation");
    }
}
