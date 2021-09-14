package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier samsung = new Supplier("Samsung", "Electronics");
        supplierDataStore.add(samsung);
        Supplier LG = new Supplier("LG", "Electronics");
        supplierDataStore.add(LG);
        Supplier sony = new Supplier("Sony", "Electronics");
        supplierDataStore.add(sony);


        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory phone = new ProductCategory("Phone", "Electronics", "Mobile Phone");
        productCategoryDataStore.add(phone);
        ProductCategory TV = new ProductCategory("TV", "Electronics", "Television");
        productCategoryDataStore.add(TV);
        ProductCategory tabletop = new ProductCategory("Tabletop Games", "Games", "Board games, card games, physical games.");
        productCategoryDataStore.add(tabletop);
        ProductCategory laptop = new ProductCategory("Laptop", "Hardware", "A portable PC that can be almost as powerful as a desktop PC.");
        productCategoryDataStore.add(laptop);
        ProductCategory calculator = new ProductCategory("Calculator", "School Supplies", "Gadget used to do basic math.");
        productCategoryDataStore.add(calculator);

        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", new BigDecimal("49.9"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Mix 700", new BigDecimal("479"), "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", new BigDecimal("89"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        productDataStore.add(new Product("Samsung Galaxy S8", new BigDecimal("777"), "USD", "Testing in progress", phone, samsung));
        productDataStore.add(new Product("LG Nanocell", new BigDecimal("1999"), "USD", "Self-lit pixels. Spectacular picture quality. A lot of design possibilities. Latest cutting edge technologies.", TV, LG));
        productDataStore.add(new Product("Sony BRAVIA XR A90J", new BigDecimal("2847"), "USD", "Revolutionary OLED images. Advanced speakers and Cognitive Processor XR. Thousands of movies and shows, voice accessible.", TV, sony));
        productDataStore.add(new Product("Cards Against Humanity", new BigDecimal("50.82"), "USD", "Cards Against Humanity is a party game for horrible people.", tabletop, amazon));
        productDataStore.add(new Product("Dell Latitude 3400", new BigDecimal("838.83"), "USD", "Dell Latitude 3400 ultraportable laptop with Intel Core i5-8265U processor up to 3.90 GHz, 14, Full HD, 8GB, 256GB SSD, Intel UHD Graphics, Ubuntu, Black", laptop, amazon));
        productDataStore.add(new Product("Collin Desk Calculator", new BigDecimal("10.99"), "USD", "Solar energy and battery backup power keep the desktop calculator", calculator, amazon));
    }
}
