package model;

import lombok.*;

import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Player {
    private int id;
    private int teamId;
    private String name;
    private String position;
    private Timestamp createdAt;
}
