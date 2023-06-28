package dao;

import lombok.AllArgsConstructor;
import model.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class PlayerDao {
    private final Connection connection;


    public int insert(Player player){
        int result = 0;
        String query = "insert into player(team_id, name, position, created_at) values(?, ?, ?, now())";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, player.getTeamId());
            statement.setString(2, player.getName());
            statement.setString(3, player.getPosition());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void update(int id){
        String query = "update player set team_id = null where id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Player> findPlayerListByTeam(int teamId){
        List<Player> playerList = new ArrayList<>();

        String query = "select * from player where team_id = ?";

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, teamId);

            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                Player player = new Player(
                        rs.getInt("id"),
                        rs.getInt("team_id"),
                        rs.getString("name"),
                        rs.getString("position"),
                        rs.getTimestamp("created_at")
                );
                playerList.add(player);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return playerList;
    }

}
