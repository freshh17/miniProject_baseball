package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@Getter
public class OutPlayer {
    private int id;
    private int playerId;
    private String reason;
    private Timestamp createdAt;
}
