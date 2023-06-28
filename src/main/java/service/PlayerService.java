package service;

import dao.PlayerDao;
import lombok.AllArgsConstructor;
import model.Player;

import java.sql.Connection;
import java.util.List;

@AllArgsConstructor
public class PlayerService {
    private final PlayerDao playerDao;
    private final Connection connection;


    //선수 등록
    public void registerPlayer(int teamId, String name, String position){
        int result = playerDao.insert(Player.builder()
                .teamId(teamId)
                .name(name)
                .position(position)
                .build());

        if(result == 1)
            System.out.println("성공");
    }

    //팀별 선수 목록
    public void getPlayerListByTeam(int teamId){
        List<Player> playerList = playerDao.findPlayerListByTeam(teamId);

        for(Player player : playerList)
            System.out.println(player);
    }
}
