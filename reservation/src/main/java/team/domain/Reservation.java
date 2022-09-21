package team.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import team.ReservationApplication;
import team.domain.Canceled;
import team.domain.ReservationCancelRequested;
import team.domain.ReservationRequested;
import team.domain.Reserved;

@Entity
@Table(name = "Reservation_table")
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long roomId;

    private Long paymentId;

    private Long customerId;

    private Date reserveDate;

    private String reserveStatus;

    @PostPersist
    public void onPostPersist() {
        ReservationRequested reservationRequested = new ReservationRequested(
            this
        );
        reservationRequested.publishAfterCommit();

        ReservationCancelRequested reservationCancelRequested = new ReservationCancelRequested(
            this
        );
        reservationCancelRequested.publishAfterCommit();

        Reserved reserved = new Reserved(this);
        reserved.publishAfterCommit();

        Canceled canceled = new Canceled(this);
        canceled.publishAfterCommit();
    }

    public static ReservationRepository repository() {
        ReservationRepository reservationRepository = ReservationApplication.applicationContext.getBean(
            ReservationRepository.class
        );
        return reservationRepository;
    }

    public static void affirmPayment(Paid paid) {
        /** Example 1:  new item 
        Reservation reservation = new Reservation();
        repository().save(reservation);

        Reserved reserved = new Reserved(reservation);
        reserved.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(paid.get???()).ifPresent(reservation->{
            
            reservation // do something
            repository().save(reservation);

            Reserved reserved = new Reserved(reservation);
            reserved.publishAfterCommit();

         });
        */

    }

    public static void affirmCancelPayment(PaymentCanceled paymentCanceled) {
        /** Example 1:  new item 
        Reservation reservation = new Reservation();
        repository().save(reservation);

        Canceled canceled = new Canceled(reservation);
        canceled.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(paymentCanceled.get???()).ifPresent(reservation->{
            
            reservation // do something
            repository().save(reservation);

            Canceled canceled = new Canceled(reservation);
            canceled.publishAfterCommit();

         });
        */

    }
}
