package team.domain;

import java.util.*;
import lombok.Data;
import team.infra.AbstractEvent;

@Data
public class ReservationCanceled extends AbstractEvent {

    private Long id;
    private Long roomId;
    private Long paymentId;
    private Long customerId;
    private Date reserveDate;
    private String reserveStatus;
}
