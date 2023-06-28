package service;

import dao.OutPlayerDao;
import dao.PlayerDao;
import dto.OutPlayerRespDto;
import lombok.AllArgsConstructor;
import model.OutPlayer;

import java.sql.Connection;
import java.util.List;

@AllArgsConstructor
public class OutPlayerService {
    private final OutPlayerDao outPlayerDao;
    private final PlayerDao playerDao;
    private final Connection connection;

    //선수 퇴출 등록
    public void registerOutPlayer(int playerId, String reason){
        int result1 = 0, result2 = 0;
        try {
            connection.setAutoCommit(false);

            OutPlayer outPlayer = OutPlayer.builder()
                    .playerId(playerId)
                    .reason(reason)
                    .build();
            //퇴출 선수 insert
            result1 = outPlayerDao.insert(outPlayer);

            //해당 선수 team_id를 null로 update
            result2 = playerDao.update(outPlayer.getPlayerId());

            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(result1 == 1 && result2 == 1)
            System.out.println("성공");
    }

    //선수 퇴출 목록
    public void getOutPlayerList(){
        List<OutPlayerRespDto> outPlayerRespDtoList = outPlayerDao.findAll();

        for(OutPlayerRespDto outPlayerRespDto : outPlayerRespDtoList)
            System.out.println(outPlayerRespDto);
    }
}
