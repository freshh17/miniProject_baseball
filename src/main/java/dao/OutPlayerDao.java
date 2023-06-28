package dao;

import dto.OutPlayerRespDto;
import lombok.AllArgsConstructor;
import model.OutPlayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class OutPlayerDao {
    private final Connection connection;

    public int insert(OutPlayer outPlayer){
        int result = 0;

        String query = "insert into out_player(player_id, reason, created_at) values(?, ?, now())";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, outPlayer.getPlayerId());
            statement.setString(2, outPlayer.getReason());

            result = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<OutPlayerRespDto> findAll(){
        List<OutPlayerRespDto> outPlayerRespDtoList = new ArrayList<>();

        String query = "select player.id, player.name, player.position, out_player.reason, out_player.created_at " +
                "from player left join out_player on player.id = out_player.player_id " +
                "union " +
                "select player.id, player.name, player.position, out_player.reason, out_player.created_at " +
                "from player right join out_player on player.id = out_player.player_id";

        try {
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                OutPlayerRespDto outPlayerRespDto = new OutPlayerRespDto(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("position"),
                        rs.getString("reason"),
                        rs.getTimestamp("created_at")
                );

                outPlayerRespDtoList.add(outPlayerRespDto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return outPlayerRespDtoList;
    }

}
