package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class OutPlayerRespDto {
    private int id;
    private String name;
    private String position;
    private String reason;
    private Timestamp created_at;

}
