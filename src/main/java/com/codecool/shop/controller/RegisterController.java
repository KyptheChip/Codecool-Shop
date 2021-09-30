package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.inDatabase.UserDaoJDBC;
import com.codecool.shop.model.User;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {
    private UserDaoJDBC userDao;
    private TemplateEngine engine;
    private WebContext context;
    private HttpSession session;

    private void setData(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        session = req.getSession();
    }

    public RegisterController() throws SQLException {
        this.userDao = new UserDaoJDBC();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setData(req, resp);
        this.engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        this.context = new WebContext(req, resp, req.getServletContext());
        engine.process("register.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setData(req, resp);
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        try {
            User user = new User(name, email, password);
            if (!userDao.checkForEmail(email)) {
                session.setAttribute("emailTaken", email);
                resp.sendRedirect("/register");
            }
            userDao.add(user);
            resp.sendRedirect("/");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
