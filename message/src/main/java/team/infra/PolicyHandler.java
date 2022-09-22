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
    MessageRepository messageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ReservationAffirmed'"
    )
    public void wheneverReservationAffirmed_SendReservationMessage(
        @Payload ReservationAffirmed reservationAffirmed
    ) {
        ReservationAffirmed event = reservationAffirmed;
        System.out.println(
            "\n\n##### listener SendReservationMessage : " +
            reservationAffirmed +
            "\n\n"
        );

        // Sample Logic //
        Message.sendReservationMessage(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ReservationCanceled'"
    )
    public void wheneverReservationCanceled_SendCancelReservationMessage(
        @Payload ReservationCanceled reservationCanceled
    ) {
        ReservationCanceled event = reservationCanceled;
        System.out.println(
            "\n\n##### listener SendCancelReservationMessage : " +
            reservationCanceled +
            "\n\n"
        );

        // Sample Logic //
        Message.sendCancelReservationMessage(event);
    }
    // keep

}
