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
            roomView.setRoomPrice(roomRegistered.getPrice());
            // view 레파지 토리에 save
            roomViewRepository.save(roomView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenRoomAffirmed_then_UPDATE_1(
        @Payload RoomAffirmed roomAffirmed
    ) {
        try {
            if (!roomAffirmed.validate()) return;
            // view 객체 조회

            List<RoomView> roomViewList = roomViewRepository.findByRoomId(
                roomAffirmed.getId()
            );
            for (RoomView roomView : roomViewList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                roomView.setRoomStatus(roomAffirmed.getStatus());
                // view 레파지 토리에 save
                roomViewRepository.save(roomView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenRoomCancled_then_UPDATE_2(
        @Payload RoomCancled roomCancled
    ) {
        try {
            if (!roomCancled.validate()) return;
            // view 객체 조회

            List<RoomView> roomViewList = roomViewRepository.findByRoomId(
                roomCancled.getId()
            );
            for (RoomView roomView : roomViewList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                roomView.setRoomStatus(roomCancled.getStatus());
                // view 레파지 토리에 save
                roomViewRepository.save(roomView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenRoomModified_then_UPDATE_3(
        @Payload RoomModified roomModified
    ) {
        try {
            if (!roomModified.validate()) return;
            // view 객체 조회

            List<RoomView> roomViewList = roomViewRepository.findByRoomId(
                roomModified.getId()
            );
            for (RoomView roomView : roomViewList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                roomView.setRoomPrice(roomModified.getPrice());
                // view 레파지 토리에 save
                roomViewRepository.save(roomView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenReservationAffirmed_then_UPDATE_4(
        @Payload ReservationAffirmed reservationAffirmed
    ) {
        try {
            if (!reservationAffirmed.validate()) return;
            // view 객체 조회

            List<RoomView> roomViewList = roomViewRepository.findByRoomId(
                reservationAffirmed.getRoomId()
            );
            for (RoomView roomView : roomViewList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                roomView.setRoomStatus(
                    String.valueOf(reservationAffirmed.getRoomId())
                );
                // view 레파지 토리에 save
                roomViewRepository.save(roomView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenReservationCanceled_then_UPDATE_5(
        @Payload ReservationCanceled reservationCanceled
    ) {
        try {
            if (!reservationCanceled.validate()) return;
            // view 객체 조회

            List<RoomView> roomViewList = roomViewRepository.findByRoomId(
                reservationCanceled.getRoomId()
            );
            for (RoomView roomView : roomViewList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                roomView.setRoomStatus(
                    String.valueOf(reservationCanceled.getRoomId())
                );
                // view 레파지 토리에 save
                roomViewRepository.save(roomView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenRoomDeleted_then_DELETE_1(
        @Payload RoomDeleted roomDeleted
    ) {
        try {
            if (!roomDeleted.validate()) return;
            // view 레파지 토리에 삭제 쿼리
            roomViewRepository.deleteByRoomId(roomDeleted.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // keep

}
