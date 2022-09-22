package team.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import team.config.kafka.KafkaProcessor;
import team.domain.*;

@Service
@Transactional
public class PolicyHandler {

    @Autowired
    ReservationRepository reservationRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PaymentAffirmed'"
    )
    public void wheneverPaymentAffirmed_AffirmReservation(
        @Payload PaymentAffirmed paymentAffirmed
    ) {
        PaymentAffirmed event = paymentAffirmed;
        System.out.println(
            "\n\n##### listener AffirmReservation : " + paymentAffirmed + "\n\n"
        );

        // Sample Logic //
        Reservation.affirmReservation(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PaymentCanceled'"
    )
    public void wheneverPaymentCanceled_CancelReservation(
        @Payload PaymentCanceled paymentCanceled
    ) {
        PaymentCanceled event = paymentCanceled;
        System.out.println(
            "\n\n##### listener CancelReservation : " + paymentCanceled + "\n\n"
        );

        // Sample Logic //
        Reservation.cancelReservation(event);
    }
    // keep

}
