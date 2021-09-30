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
            ProductCategoryDaoJDBC productCategoryDaoJDBC = new ProductCategoryDaoJDBC();
            SupplierDaoJDBC supplierDaoJDBC = new SupplierDaoJDBC();
            String sql = "SELECT * FROM products WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            ProductCategory category = productCategoryDaoJDBC.find(rs.getInt(6));
            Supplier supplier = supplierDaoJDBC.find(rs.getInt(7));
            return new Product(rs.getInt(1), rs.getString(2), rs.getBigDecimal(4),
                    rs.getString(5), rs.getString(3), category, supplier);
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
            String sql = "SELECT * FROM products";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<Product> result = new ArrayList<>();
            while (rs.next()) {
                ProductCategory category = productCategoryDaoJDBC.find(rs.getInt(6));
                Supplier supplier = supplierDaoJDBC.find(rs.getInt(7));
                Product product = new Product(rs.getInt(1), rs.getString(2), rs.getBigDecimal(4),
                        rs.getString(5), rs.getString(3), category, supplier);
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
            String sql = "SELECT * FROM products WHERE product_supplier = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, spl.getId());
            ResultSet rs = st.executeQuery();
            List<Product> result = new ArrayList<>();
            while (rs.next()) {
                ProductCategory category = productCategoryDaoJDBC.find(rs.getInt(6));
                Product product = new Product(rs.getInt(1), rs.getString(2), rs.getBigDecimal(4),
                        rs.getString(5), rs.getString(3), category, spl);
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
            String sql = "SELECT * FROM products WHERE product_category = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, productCategory.getId());
            ResultSet rs = st.executeQuery();
            List<Product> result = new ArrayList<>();
            while (rs.next()) {
                Supplier supplier = supplierDaoJDBC.find(rs.getInt(7));
                Product product = new Product(rs.getInt(1) ,rs.getString(2), rs.getBigDecimal(4),
                        rs.getString(5), rs.getString(3), productCategory, supplier);
                result.add(product);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all products", e);
        }
    }
}
