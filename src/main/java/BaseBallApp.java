import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BaseBallApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("어떤 기능을 요청하시겠습니까?");
        String input = sc.nextLine();

        if(input.equals("야구장목록")){
            //야구장 목록 메소드 호출
            return;
        }else if(input.equals("팀목록")){

            return;
        }else if(input.equals("퇴출목록")){

            return;
        }else if(input.equals("포지션별 목록")){

            return;
        }

        //파싱해야 하는 요청이 들어왔을 때
        String result[] = input.split("\\?");

        String request = result[0]; // 야구장등록, 선수등록 ...

        String rawData = result[1]; //teamId=1&name=홍길동&...
        String[] output = rawData.split("&"); //output[0] = teamId=1, output[1] = name=홍길동

        List<String[]> data = new ArrayList<>();
        for(int i = 0; i < output.length; i++){
            data.add(output[i].split("=")); // [0] -> 변수명 , [1] -> 데이터
        }



    }
}
