package team.domain;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.*;
import lombok.Data;
import team.ReservationApplication;
import team.domain.ReservationAffirmed;
import team.domain.ReservationCancelRequested;
import team.domain.ReservationCanceled;
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
        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.
/*
 * 
 team.external.Payment payment = new team.external.Payment();
 // mappings goes here
 ReservationApplication.applicationContext
 .getBean(team.external.PaymentService.class)
 .requestPayment(payment);
 */
        this.setReserveStatus("예약요청");
        ReservationRequested reservationRequested = new ReservationRequested(
            this
        );
        reservationRequested.publishAfterCommit();
/*
        ReservationCancelRequested reservationCancelRequested = new ReservationCancelRequested(
            this
        );
        reservationCancelRequested.publishAfterCommit();

        ReservationAffirmed reservationAffirmed = new ReservationAffirmed(this);
        reservationAffirmed.publishAfterCommit();

        ReservationCanceled reservationCanceled = new ReservationCanceled(this);
        reservationCanceled.publishAfterCommit();
    */
    }

    public static ReservationRepository repository() {
        ReservationRepository reservationRepository = ReservationApplication.applicationContext.getBean(
            ReservationRepository.class
        );
        return reservationRepository;
    }

    public static void affirmReservation(PaymentAffirmed paymentAffirmed) {
        /** Example 1:  new item   */
        Optional<Reservation> optionalReservation = repository().findById(paymentAffirmed.getReservationId());
        Reservation reservation = optionalReservation.get();
        reservation.setPaymentId(paymentAffirmed.getId());
        reservation.setReserveStatus("예약완료");
       // reservation.setId(paymentAffirmed.getReservationId());
        
        repository().save(reservation);

        ReservationAffirmed reservationAffirmed = new ReservationAffirmed(reservation);
        reservationAffirmed.publishAfterCommit();
      

        /** Example 2:  finding and process
        
        repository().findById(paymentAffirmed.get???()).ifPresent(reservation->{
            
            reservation // do something
            repository().save(reservation);

            ReservationAffirmed reservationAffirmed = new ReservationAffirmed(reservation);
            reservationAffirmed.publishAfterCommit();

         });
        */

    }

    public static void cancelReservation(PaymentCanceled paymentCanceled) {
        /** Example 1:  new item 
        Reservation reservation = new Reservation();
        repository().save(reservation);

        ReservationCanceled reservationCanceled = new ReservationCanceled(reservation);
        reservationCanceled.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(paymentCanceled.get???()).ifPresent(reservation->{
            
            reservation // do something
            repository().save(reservation);

            ReservationCanceled reservationCanceled = new ReservationCanceled(reservation);
            reservationCanceled.publishAfterCommit();

         });
        */

    }

    public void requestReservation() {
        this.setReserveStatus("예약요청/결제대기");
        ReservationRequested reservationRequested = new ReservationRequested(
            this
        );
        reservationRequested.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        team.external.Payment payment = new team.external.Payment();
        // mappings goes here
        ReservationApplication.applicationContext
            .getBean(team.external.PaymentService.class)
            .requestPayment(payment);
    }
     
}
