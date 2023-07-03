package dao;

import model.Team;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamDao {
    public List<Team> select() {
        Connection connection = DBConnection.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        List<Team> teamList = new ArrayList<>();

        try {

            String query = "SELECT id, stadium_id, name, created_at FROM team";

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Team team = new Team();
                team.setId(resultSet.getInt("id"));
                team.setStadiumId(resultSet.getInt("stadium_id"));
                team.setName(resultSet.getString("name"));
                team.setCreatedAt(resultSet.getTimestamp("created_at"));

                teamList.add(team);
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

        return teamList;
    }

    public int insert(Integer stadiumId, String name) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        int result = 0;

        try {
            String query = "INSERT INTO team (stadium_id, name, created_at) VALUES (?, ?, NOW())";
            statement = connection.prepareStatement(query);
            statement.setInt(1, stadiumId);
            statement.setString(2, name);
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
