package com.codecool.shop.dao.inDatabase;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJDBC implements ProductDao {
    private DataSource dataSource;


    public ProductDaoJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Product product) {
    }

    @Override
    public Product find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM product WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) { // first row was not found == no data was returned by the query
                return null;
            }
            return new Product(rs.getString(2), rs.getBigDecimal(3),
                    rs.getString(4), rs.getString(5), rs.getObject(6, ProductCategory.class), rs.getObject(7, Supplier.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            ProductCategoryDaoJDBC productCategoryDaoJDBC = new ProductCategoryDaoJDBC();
            SupplierDaoJDBC supplierDaoJDBC = new SupplierDaoJDBC();
            String sql = "SELECT * FROM product";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<Product> result = new ArrayList<>();
            while (rs.next()) {
                ProductCategory category = productCategoryDaoJDBC.find(rs.getInt(6));
                Supplier supplier = supplierDaoJDBC.find(rs.getInt(7));
                Product product = new Product(rs.getString(3), rs.getBigDecimal(2),
                        rs.getString(4), rs.getString(5), category, supplier);
                result.add(product);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all products", e);
        }
    }

    @Override
    public List<Product> getProductsBySupplier(Supplier spl) {
        try (Connection conn = dataSource.getConnection()) {
            ProductCategoryDaoJDBC productCategoryDaoJDBC = new ProductCategoryDaoJDBC();
            String sql = "SELECT * FROM product WHERE supplier = ?";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<Product> result = new ArrayList<>();
            while (rs.next()) { // while result set pointer is positioned before or on last row read authors
                ProductCategory category = productCategoryDaoJDBC.find(rs.getInt(6));
                Product product = new Product(rs.getString(3), rs.getBigDecimal(2),
                        rs.getString(4), rs.getString(5), category, spl);
                result.add(product);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all products", e);
        }
    }

    @Override
    public List<Product> getProductsByCategory(ProductCategory productCategory) {
        try (Connection conn = dataSource.getConnection()) {
            SupplierDaoJDBC supplierDaoJDBC = new SupplierDaoJDBC();
            String sql = "SELECT * FROM product WHERE category = ?";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<Product> result = new ArrayList<>();
            while (rs.next()) { // while result set pointer is positioned before or on last row read authors
                Supplier supplier = supplierDaoJDBC.find(rs.getInt(7));
                Product product = new Product(rs.getString(3), rs.getBigDecimal(2),
                        rs.getString(4), rs.getString(5), productCategory, supplier);
                result.add(product);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all products", e);
        }
    }
}
