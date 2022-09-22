package team.domain;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.*;
import lombok.Data;
import team.RoomApplication;
import team.domain.RoomAffirmed;
import team.domain.RoomCancled;
import team.domain.RoomDeleted;
import team.domain.RoomModified;
import team.domain.RoomRegistered;

@Entity
@Table(name = "Room_table")
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long reservationId;
    private String status;
    private Double price;
    private Long reviewCnt;

    public static RoomRepository repository() {
        RoomRepository roomRepository = RoomApplication.applicationContext.getBean(RoomRepository.class);
        return roomRepository;
    }

    @PostPersist
    public void onPostPersist() {
        RoomRegistered roomRegistered = new RoomRegistered(this);
        roomRegistered.setStatus("등록됨");
        roomRegistered.setReviewCnt(0L);
        roomRegistered.publishAfterCommit();
    }
    /*
     * 
     RoomAffirmed roomAffirmed = new RoomAffirmed(this);
     roomAffirmed.publishAfterCommit();
     
     RoomCancled roomCancled = new RoomCancled(this);
     roomCancled.publishAfterCommit();
     */

    @PreUpdate
    public void onPreUpdate() {
        RoomModified roomModified = new RoomModified(this);
        roomModified.publishAfterCommit();
    }
 
    @PostRemove
    public void onPostRemove() {
        RoomDeleted roomDeleted = new RoomDeleted(this);
        roomDeleted.publishAfterCommit();
    }

    public void registerRoom() {
        RoomRegistered roomRegistered = new RoomRegistered(this);
        roomRegistered.publishAfterCommit();
    }

    public static void updateReviewCnt(ReviewRegistered reviewRegistered) {
        /** Example 1:  new item */
        Room room = new Room();
        room.setId(reviewRegistered.getRoomId());
        Long reviewCnt = 0L;
        Optional<Room> room2 = repository().findById(room.getId());
        if(null != room2.get().getReviewCnt()){
            reviewCnt = room2.get().getReviewCnt();
        }
       // room.setReviewCnt(reviewCnt++);;
       room.setReviewCnt(++reviewCnt);
        
        repository().save(room);

       

        /** Example 2:  finding and process
        
        repository().findById(reviewRegistered.get???()).ifPresent(room->{
            
            room // do something
            repository().save(room);


         });
        */

    }

    public static void updateReviewCnt(ReviewDeleted reviewDeleted) {
        /** Example 1:  new item */
        Room room = new Room();
        room.setId(reviewDeleted.getRoomId());
        Long reviewCnt = 0L;
        Optional<Room> room2 = repository().findById(room.getId());
        if(null != room2.get().getReviewCnt()){
            reviewCnt = room2.get().getReviewCnt();
        }
        room.setReviewCnt(--reviewCnt);
        
        repository().save(room);

        
        /** Example 2:  finding and process
        
        repository().findById(reviewDeleted.get???()).ifPresent(room->{
            
            room // do something
            repository().save(room);


         });
        */
    }

    public static void affirmRoom(ReservationAffirmed reservationAffirmed) {
        /** Example 1:  new item 
        Room room = new Room();
        repository().save(room);

        RoomAffirmed roomAffirmed = new RoomAffirmed(room);
        roomAffirmed.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(reservationAffirmed.get???()).ifPresent(room->{
            
            room // do something
            repository().save(room);

            RoomAffirmed roomAffirmed = new RoomAffirmed(room);
            roomAffirmed.publishAfterCommit();

         });
        */
    }

    public static void cancelRoom(ReservationCanceled reservationCanceled) {
        /** Example 1:  new item 
        Room room = new Room();
        repository().save(room);

        RoomCancled roomCancled = new RoomCancled(room);
        roomCancled.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(reservationCanceled.get???()).ifPresent(room->{
            
            room // do something
            repository().save(room);

            RoomCancled roomCancled = new RoomCancled(room);
            roomCancled.publishAfterCommit();

         });
        */
    }
}
