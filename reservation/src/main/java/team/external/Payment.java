package team.external;

import java.util.Date;
import lombok.Data;

@Data
public class Payment {

    private Long id;
    private Long reservationId;
    private Integer paymentAmount;
    private Date paymentDate;
    private String paymentStatus;
    // keep

}
