package team.domain;

import java.util.*;
import lombok.*;
import team.domain.*;
import team.infra.AbstractEvent;

@Data
@ToString
public class RoomAffirmed extends AbstractEvent {

    private Long id;
    private Long reservationId;
    private String status;
    private Double price;
    private Long reviewCnt;

    public RoomAffirmed(Room aggregate) {
        super(aggregate);
    }

    public RoomAffirmed() {
        super();
    }
    // keep

}
