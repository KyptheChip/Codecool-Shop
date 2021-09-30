package com.codecool.shop.dao.inDatabase;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;

import javax.sql.DataSource;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class UserDaoJDBC implements UserDao {
    private DataSource dataSource = new DatabaseManager().connect();

    public UserDaoJDBC() throws SQLException {
    }

    @Override
    public void add(User user) {
        try (Connection conn = dataSource.getConnection()) {
            String name = user.getName();
            String email = user.getEmail();
            String  password = user.getPassword();
            String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, crypt(?, gen_salt('bf')))";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, name);
            st.setString(2, email);
            st.setString(3, password);
            int id = st.executeUpdate();
            user.setID(id);
        } catch (SQLException e) {
            System.out.println("a fost odata ca in povesti");
            throw new RuntimeException(e);
        }
    }

    @Override
    public User find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT name, email, password FROM users WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            String name = rs.getString(1);
            String email = rs.getString(2);
            String password = rs.getString(3);
            return new User(id, name, email, password);
        } catch (SQLException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findByEmailOrName(String nameOrEmail) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM users WHERE name = ? OR email = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, nameOrEmail);
            st.setString(2, nameOrEmail);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String email = rs.getString(3);
            String password = rs.getString(4);
            return new User(id, name, email, password);
        } catch (SQLException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateName(String name, String email) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE users SET name = ? WHERE email = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, email);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updatePassword(String password, String email) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE users SET password = ? WHERE email = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, password);
            st.setString(2, email);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM users WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeByEmail(String email) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM users WHERE email = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, email);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean checkPassword(String password) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT crypt(?, password) FROM users WHERE password = crypt(?, password)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, password);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return false;
            }
            System.out.println(rs.getString(1));
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean checkForEmail(String email) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT email FROM users WHERE email LIKE ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            return !rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
