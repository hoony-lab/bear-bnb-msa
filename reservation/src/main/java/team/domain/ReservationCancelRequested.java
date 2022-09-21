package team.domain;

import java.util.*;
import lombok.*;
import team.domain.*;
import team.infra.AbstractEvent;

@Data
@ToString
public class ReservationCancelRequested extends AbstractEvent {

    private Long id;
    private Long roomId;
    private Long paymentId;
    private Long customerId;
    private Date reserveDate;
    private String reserveStatus;

    public ReservationCancelRequested(Reservation aggregate) {
        super(aggregate);
    }

    public ReservationCancelRequested() {
        super();
    }
    // keep

}
