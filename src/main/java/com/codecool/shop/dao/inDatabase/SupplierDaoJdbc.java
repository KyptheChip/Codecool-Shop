package com.codecool.shop.dao.inDatabase;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJdbc implements SupplierDao {
    private DataSource dataSource = new DatabaseManager().connect();

    public SupplierDaoJdbc() throws SQLException {
    }

    @Override
    public void add(Supplier supplier) {
    }

    @Override
    public Supplier find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT supplier_name FROM suppliers WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next())
                return null;
            return new Supplier(rs.getString(1));
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void remove(int id) {
    }

    @Override
    public List<Supplier> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM suppliers";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            List<Supplier> supplierList = new ArrayList<>();
            while (rs.next()) {
                Supplier supplier = new Supplier(rs.getString(2));
                supplierList.add(supplier);
            }
            return supplierList;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
