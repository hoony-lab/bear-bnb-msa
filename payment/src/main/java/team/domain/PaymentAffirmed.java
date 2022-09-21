package team.domain;

import java.util.*;
import lombok.*;
import team.domain.*;
import team.infra.AbstractEvent;

@Data
@ToString
public class PaymentAffirmed extends AbstractEvent {

    private Long id;
    private Long reservationId;
    private Integer paymentAmount;
    private Date paymentDate;
    private String paymentStatus;

    public PaymentAffirmed(Payment aggregate) {
        super(aggregate);
    }

    public PaymentAffirmed() {
        super();
    }
    // keep

}
