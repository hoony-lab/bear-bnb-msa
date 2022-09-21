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
    RoomRepository roomRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ReviewRegistered'"
    )
    public void wheneverReviewRegistered_UpdateReviewCnt(
        @Payload ReviewRegistered reviewRegistered
    ) {
        ReviewRegistered event = reviewRegistered;
        System.out.println(
            "\n\n##### listener UpdateReviewCnt : " + reviewRegistered + "\n\n"
        );

        // Sample Logic //
        Room.updateReviewCnt(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ReviewDeleted'"
    )
    public void wheneverReviewDeleted_UpdateReviewCnt(
        @Payload ReviewDeleted reviewDeleted
    ) {
        ReviewDeleted event = reviewDeleted;
        System.out.println(
            "\n\n##### listener UpdateReviewCnt : " + reviewDeleted + "\n\n"
        );

        // Sample Logic //
        Room.updateReviewCnt(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Reserved'"
    )
    public void wheneverReserved_AffirmRoom(@Payload Reserved reserved) {
        Reserved event = reserved;
        System.out.println(
            "\n\n##### listener AffirmRoom : " + reserved + "\n\n"
        );

        // Sample Logic //
        Room.affirmRoom(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Canceled'"
    )
    public void wheneverCanceled_CancelRoom(@Payload Canceled canceled) {
        Canceled event = canceled;
        System.out.println(
            "\n\n##### listener CancelRoom : " + canceled + "\n\n"
        );

        // Sample Logic //
        Room.cancelRoom(event);
    }
    // keep

}
