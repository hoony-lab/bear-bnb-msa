package team.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import team.ReservationApplication;
import team.domain.PaymentAffirmed;
import team.domain.PaymentCanceled;
import team.domain.ReservationCancelRequested;
import team.domain.ReservationRequested;

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

        PaymentAffirmed paymentAffirmed = new PaymentAffirmed(this);
        paymentAffirmed.publishAfterCommit();

        PaymentCanceled paymentCanceled = new PaymentCanceled(this);
        paymentCanceled.publishAfterCommit();
    }

    public static ReservationRepository repository() {
        ReservationRepository reservationRepository = ReservationApplication.applicationContext.getBean(
            ReservationRepository.class
        );
        return reservationRepository;
    }

    public static void affirmPayment(PaymentAffirmed paymentAffirmed) {
        /** Example 1:  new item 
        Reservation reservation = new Reservation();
        repository().save(reservation);

        PaymentAffirmed paymentAffirmed = new PaymentAffirmed(reservation);
        paymentAffirmed.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(paymentAffirmed.get???()).ifPresent(reservation->{
            
            reservation // do something
            repository().save(reservation);

            PaymentAffirmed paymentAffirmed = new PaymentAffirmed(reservation);
            paymentAffirmed.publishAfterCommit();

         });
        */

    }

    public static void cancelPayment(PaymentCanceled paymentCanceled) {
        /** Example 1:  new item 
        Reservation reservation = new Reservation();
        repository().save(reservation);

        PaymentCanceled paymentCanceled = new PaymentCanceled(reservation);
        paymentCanceled.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(paymentCanceled.get???()).ifPresent(reservation->{
            
            reservation // do something
            repository().save(reservation);

            PaymentCanceled paymentCanceled = new PaymentCanceled(reservation);
            paymentCanceled.publishAfterCommit();

         });
        */

    }
}
