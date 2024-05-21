package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private static final String URL = "jdbc:h2:~/ecommerce";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public ProductDAO() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS Product (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "type VARCHAR(255), " +
                    "description VARCHAR(255), " +
                    "weight DOUBLE, " +
                    "quantity INT, " +
                    "unitOfMeasure VARCHAR(50))";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addProduct(Product product) {
        String sql = "INSERT INTO Product (type, description, weight, quantity, unitOfMeasure) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getType());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getWeight());
            statement.setInt(4, product.getQuantity());
            statement.setString(5, product.getUnitOfMeasure());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Product getProduct(int id) {
        String sql = "SELECT * FROM Product WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Product(
                        resultSet.getString("type"),
                        resultSet.getString("description"),
                        resultSet.getDouble("weight"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("unitOfMeasure"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateProduct(int id, Product product) {
        String sql = "UPDATE Product SET type = ?, description = ?, weight = ?, quantity = ?, unitOfMeasure = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getType());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getWeight());
            statement.setInt(4, product.getQuantity());
            statement.setString(5, product.getUnitOfMeasure());
            statement.setInt(6, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int id) {
        String sql = "DELETE FROM Product WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Product";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getString("type"),
                        resultSet.getString("description"),
                        resultSet.getDouble("weight"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("unitOfMeasure")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}