package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.inDatabase.DatabaseManager;
import com.codecool.shop.dao.inDatabase.ProductCategoryDaoJDBC;
import com.codecool.shop.dao.inDatabase.ProductDaoJDBC;
import com.codecool.shop.dao.inDatabase.SupplierDaoJDBC;
import com.codecool.shop.dao.inMemory.ProductCategoryDaoMem;
import com.codecool.shop.dao.inMemory.ProductDaoMem;
import com.codecool.shop.dao.inMemory.SupplierDaoMem;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {
    ProductDao productDataStore = ProductDaoMem.getInstance();
    ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
    SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
    ProductService productService = new ProductService(productDataStore, productCategoryDataStore, supplierDataStore);
    TemplateEngine engine;
    WebContext context;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore;
        try {
            DataSource dataSource = new DatabaseManager().connect();
            if (DatabaseManager.getDao().equals("memory")) {
                productDataStore = ProductDaoMem.getInstance();
                ProductCategoryDao productCategoryDa0 = ProductCategoryDaoMem.getInstance();
                SupplierDao supplierDao = SupplierDaoMem.getInstance();
                ProductService productService = new ProductService(productDataStore, productCategoryDa0, supplierDao);
            } else {
                productDataStore = new ProductDaoJDBC(dataSource);
                ProductCategoryDao productCategoryDao = new ProductCategoryDaoJDBC();
                SupplierDao supplierDao = new SupplierDaoJDBC();
                ProductService productService = new ProductService(productDataStore, productCategoryDao, supplierDao);
            }

            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            WebContext context = new WebContext(req, resp, req.getServletContext());

            context.setVariable("categories", productService.getAllProductCategories());
            context.setVariable("suppliers", productService.getAllSuppliers());
            context.setVariable("products", productService.getProductsByCategoryId(1));
            engine.process("product/index.html", context, resp.getWriter());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void setEngine(HttpServletRequest request) {
        this.engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
    }

    private void setContext(HttpServletRequest request, HttpServletResponse response) {
        this.context = new WebContext(request, response, request.getServletContext());
    }

    private void setContextVariablesByRequestParameters(HttpServletRequest request) {
        var categoryParameter = request.getParameter("category");
        var supplierParameter = request.getParameter("supplier");

        if (supplierParameter != null) {
            int id = getIdParameter(supplierParameter);
            if (id > 0) {
                var supplier = productService.getSupplierById(id);
                var products = productService.getProductsBySupplierId(id);
                context.setVariable("products", supplier == null ? productService.getProductsBySupplierId(1) : products);
                context.setVariable("supplier", supplier == null ? productService.getSupplierById(1) : supplier);
            }
        } else if (categoryParameter != null){
            int id = getIdParameter(categoryParameter);
            if (id > 0) {
                var category = productService.getProductCategory(id);
                var products = productService.getProductsByCategoryId(id);
                context.setVariable("products", category == null ? productService.getProductsByCategoryId(1) : products);
                context.setVariable("category", category == null ? productService.getProductCategory(1) : category);
            }
        } else {
            setMainDefaultContext();
        }
    }

    private void setMenuContext() {
        context.setVariable("categories", productService.getAllProductCategories());
        context.setVariable("suppliers", productService.getAllSuppliers());
    }

    private void setMainDefaultContext() {
        context.setVariable("category", productService.getProductCategory(1));
        context.setVariable("products", productService.getProductsByCategoryId(1));
    }

    private int getIdParameter(String idParameter) {
        int id;
        try {
            id = Integer.parseInt(idParameter);
        } catch (Exception e) {
            id = -1;
        }
        return id;
    }
}
