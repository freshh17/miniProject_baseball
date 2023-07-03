package service;

import dao.TeamDao;
import model.Team;

import java.util.List;

// 팀 서비스
public class TeamService {
    private TeamDao teamDao = new TeamDao();

    // 팀 목록 전체 조회 @return
    public List<Team> select() {
        return teamDao.select();
    }

    // 팀 등록 @param stadiumID name @return
    public  int insert(Integer stadiumId, String name) {
        return teamDao.insert(stadiumId, name);
    }
}
