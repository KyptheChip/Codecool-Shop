package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.inMemory.ProductCategoryDaoMem;
import com.codecool.shop.dao.inMemory.ProductDaoMem;
import com.codecool.shop.dao.inMemory.SupplierDaoMem;
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
        Supplier amazon = new Supplier("Amazon");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo");
        supplierDataStore.add(lenovo);
        Supplier samsung = new Supplier("Samsung");
        supplierDataStore.add(samsung);
        Supplier LG = new Supplier("LG");
        supplierDataStore.add(LG);
        Supplier sony = new Supplier("Sony");
        supplierDataStore.add(sony);
        Supplier apple = new Supplier("Apple");
        supplierDataStore.add(apple);


        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablets");
        productCategoryDataStore.add(tablet);
        ProductCategory phone = new ProductCategory("Phones");
        productCategoryDataStore.add(phone);
        ProductCategory TV = new ProductCategory("TVs");
        productCategoryDataStore.add(TV);
        ProductCategory tabletop = new ProductCategory("Tabletop Games");
        productCategoryDataStore.add(tabletop);
        ProductCategory laptop = new ProductCategory("Laptops");
        productCategoryDataStore.add(laptop);

        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", new BigDecimal("49.9"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Mix 700", new BigDecimal("479"), "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", new BigDecimal("89"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        productDataStore.add(new Product("Samsung Galaxy S8", new BigDecimal("777"), "USD", "Testing in progress", phone, samsung));
        productDataStore.add(new Product("LG Nanocell", new BigDecimal("1999"), "USD", "Self-lit pixels. Spectacular picture quality. A lot of design possibilities. Latest cutting edge technologies.", TV, LG));
        productDataStore.add(new Product("Sony BRAVIA XR A90J", new BigDecimal("2847"), "USD", "Revolutionary OLED images. Advanced speakers and Cognitive Processor XR. Thousands of movies and shows, voice accessible.", TV, sony));
        productDataStore.add(new Product("Cards Against Humanity", new BigDecimal("50.82"), "USD", "Cards Against Humanity is a party game for horrible people.", tabletop, amazon));
        productDataStore.add(new Product("Dell Latitude 3400", new BigDecimal("838.83"), "USD", "Dell Latitude 3400 ultraportable laptop with Intel Core i5-8265U processor up to 3.90 GHz, 14, Full HD, 8GB, 256GB SSD, Intel UHD Graphics, Ubuntu, Black", laptop, amazon));
        productDataStore.add(new Product("Dell Latitude 5400", new BigDecimal("644"), "USD", "Redesigned and ready for professional use. A powerful and productive device. Beauty in every detail.", laptop, amazon));
        productDataStore.add(new Product("SAMSUNG Galaxy Tab A7", new BigDecimal("221.87"), "USD", "All the power you need for an epic game. You store more than you like. Wonderful screen and a rich sound", tablet, samsung));
        productDataStore.add(new Product("SAMSUNG Galaxy Tab S7 FE", new BigDecimal("723.31"), "USD", "Your favorite shows, movies and videos, seen in a new perspective. The fastest processor of a Galaxy Tab. Smart battery that lasts all day.", tablet, samsung));
        productDataStore.add(new Product("iPhone 12 Pro", new BigDecimal("999"), "USD", "A superpowerful chip. 5G speed. An advanced dual-camera system. A tough Ceramic Shield. And a bright, beautiful OLED display. iPhone 12 has it all.", phone, apple));
        productDataStore.add(new Product("OnePlus 8 Pro", new BigDecimal("942"), "USD", "Powerful 5G performance. Enhanced display. Has OxygenOS which is smart, fast and efficient, with intuitive functions designed especially for you.", phone, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Flex 5", new BigDecimal("679"), "USD", "Extraordinary blend of performance and value. One of the best popularly priced 2-in-1 convertibles", laptop, lenovo));
        productDataStore.add(new Product("Asus VivoBook 17 M712", new BigDecimal("649"), "USD", "Large, sunny screen. Stylish, modern design. Stylish, modern design", laptop, amazon));
        productDataStore.add(new Product("Xiaomi Mi 10T Pro", new BigDecimal("599"), "USD", "Significantly improved gaming experience. An AdaptiveSync smart display. High capacity battery (5000 mAh).", phone, amazon));
    }
}
