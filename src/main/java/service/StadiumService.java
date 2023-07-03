package service;

import dao.StadiumDao;
import model.Stadium;

import java.util.List;

//야구장 서비스
public class StadiumService {

    private StadiumDao stadiumDao = new StadiumDao();

    // 야구장 목록 조회 @return
    public List<Stadium> select() {
        return stadiumDao.select();
    }

    // 야구장 등록 @param name @return
    public int insert(String name) {
        return stadiumDao.insert(name);
    }
}
