import model.Stadium;
import model.Team;

import service.StadiumService;
import service.TeamService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BaseBallApp {

    public static void main(String[] args) {
        // 서비스 선언
        StadiumService stadiumService = new StadiumService();
        TeamService teamService = new TeamService();

        Scanner sc = new Scanner(System.in);
        
        System.out.print("어떤 기능을 요청하시겠습니까?");
        String str = sc.nextLine();
        String[] strs = str.split("\\?");

        // 기능
        String feature = strs[0];

        // 파라미터
        Map<String, String> params = null;
        if (strs.length > 1) {
            params = new HashMap<>();
            strs = strs[1].split("&");
            for (String s : strs) {
                String[] t = s.split("=");
                params.put(t[0], t[1]);
            }
        }

        switch (feature) {
            case "야구장등록":{
                int result = stadiumService.insert(params.get("name"));
                if( result > 0) {
                    System.out.println("성공");
                }else{
                    System.out.println("실패");
                }
            } break;
            case "야구장목록":{
                List<Stadium> stadiumList = stadiumService.select();
                System.out.printf("%-10s|%-10s|%-10s\n", "아이디", "이름", "생성일");
                for (Stadium stadium : stadiumList) {
                    System.out.printf("%-10s%-10s%-10s\n", stadium.getId(), stadium.getName(), stadium.getCreatedAt());
                }

            } break;
            case "팀등록":{
                int result = teamService.insert( Integer.parseInt(params.get("stadiumId")), params.get("name"));
                if( result > 0) {
                    System.out.println("성공");
                }else{
                    System.out.println("실패");
                }
            } break;
            case "팀목록":{
                List<Team> teamList = teamService.select();
                System.out.printf("%-10s|%-10s|%-10s|%-10s\n", "아이디", "야구장 아이디", "이름", "생성일");
                for (Team team : teamList) {
                    System.out.printf("%-10s%-10s%-10s%-10s\n", team.getId(), team.getStadiumId(), team.getName(), team.getCreatedAt());
                }

            } break;
        }
    }
}
