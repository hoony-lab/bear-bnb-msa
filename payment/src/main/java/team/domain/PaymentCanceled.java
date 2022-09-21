package team.domain;

import java.util.*;
import lombok.*;
import team.domain.*;
import team.infra.AbstractEvent;

@Data
@ToString
public class PaymentCanceled extends AbstractEvent {

    private Long id;
    private Long reservationId;
    private Integer paymentAmount;
    private Date paymentDate;
    private String paymentStatus;

    public PaymentCanceled(Payment aggregate) {
        super(aggregate);
    }

    public PaymentCanceled() {
        super();
    }
    // keep

}
