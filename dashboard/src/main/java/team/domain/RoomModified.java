package team.domain;

import java.util.*;
import lombok.Data;
import team.infra.AbstractEvent;

@Data
public class RoomModified extends AbstractEvent {

    private Long id;
    private Long reservationId;
    private String status;
    private Double price;
    private Long reviewCnt;
}
