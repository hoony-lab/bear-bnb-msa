package team.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "RoomView_table")
@Data
public class RoomView {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private Long roomId;
    private Long reservationId;
    private Long paymentId;
    private String roomStatus;
    private Double roomPrice;
    private Integer reviewCnt;
    private String reservationStatus;
    private String payStatus;
}
