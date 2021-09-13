package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.List;

public class ProductService{
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;
    private SupplierDao supplierDao;

    public ProductService(ProductDao productDao, ProductCategoryDao productCategoryDao, SupplierDao supplierDao) {
        this.productDao = productDao;
        this.productCategoryDao = productCategoryDao;
        this.supplierDao = supplierDao;
    }

    public ProductCategory getProductCategory(int categoryId){
        return productCategoryDao.find(categoryId);
    }

    public Supplier getSupplierById(int supplierId) { return supplierDao.find(supplierId); }

    public List<Product> getProductsByCategoryId(int categoryId){
        var category = productCategoryDao.find(categoryId);
        return productDao.getProductsByCategory(category);
    }

    public List<Product> getProductsBySupplierId(int supplierId) {
        var supplier = supplierDao.find(supplierId);
        return productDao.getProductsBySupplier(supplier);
    }

    public List<Supplier> getAllSuppliers() { return supplierDao.getAll(); }

    public List<ProductCategory> getAllProductCategories() { return productCategoryDao.getAll(); }

}
