package team.domain;

import team.domain.*;
import team.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class RoomCancled extends AbstractEvent {

    private Long id;
    private Long reservationId;
    private String status;
    private Double price;
    private Long reviewCnt;

    public RoomCancled(Room aggregate){
        super(aggregate);
    }
    public RoomCancled(){
        super();
    }
    // keep

}
