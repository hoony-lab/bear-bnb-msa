package team.infra;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import team.config.kafka.KafkaProcessor;
import team.domain.*;

@Service
public class RoomViewViewHandler {

    @Autowired
    private RoomViewRepository roomViewRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenRoomRegistered_then_CREATE_1(
        @Payload RoomRegistered roomRegistered
    ) {
        try {
            if (!roomRegistered.validate()) return;

            // view 객체 생성
            RoomView roomView = new RoomView();
            // view 객체에 이벤트의 Value 를 set 함
            roomView.setId(roomRegistered.getId());
            roomView.setRoomStatus(roomRegistered.getStatus());
            // view 레파지 토리에 save
            roomViewRepository.save(roomView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenReserved_then_UPDATE_1(@Payload Reserved reserved) {
        try {
            if (!reserved.validate()) return;
            // view 객체 조회

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // keep

}
