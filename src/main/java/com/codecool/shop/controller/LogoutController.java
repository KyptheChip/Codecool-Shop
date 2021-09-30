package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
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

@WebServlet(urlPatterns = {"/logout"})
public class LogoutController extends HttpServlet {

    private HttpSession session;
    private TemplateEngine engine;
    private WebContext context;

    private void setData(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        session = req.getSession();
        session.removeAttribute("user");
        session.removeAttribute("cart");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setData(req, resp);
        resp.sendRedirect("/");

//        this.engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
//        this.context = new WebContext(req, resp, req.getServletContext());
//        engine.process("/product/index.html", context, resp.getWriter());
    }
}
