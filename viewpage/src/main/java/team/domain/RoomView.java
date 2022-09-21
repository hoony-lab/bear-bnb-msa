package team.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Date;
import lombok.Data;

@Entity
@Table(name="RoomView_table")
@Data
public class RoomView {

        @Id
        //@GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;
        private String roomStatus;
        private Integer reviewCnt;
        private String reservationStatus;


}