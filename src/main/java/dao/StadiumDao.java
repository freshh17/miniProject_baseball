package dao;

import model.Stadium;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StadiumDao {
    public List<Stadium> select() {

        Connection connection = DBConnection.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        List<Stadium> stadiumList = new ArrayList<>();

        try {

            String query = "SELECT id, name, created_at FROM stadium";

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Stadium stadium = new Stadium();
                stadium.setId(resultSet.getInt("id"));
                stadium.setName(resultSet.getString("name"));
                stadium.setCreatedAt(resultSet.getTimestamp("created_at"));

                stadiumList.add(stadium);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
                if (resultSet != null && !resultSet.isClosed()) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return stadiumList;
    }

    public int insert(String name) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        int result = 0;

        try {
            String query = "INSERT INTO stadium (name, created_at) VALUES (?, NOW())";
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            result = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
