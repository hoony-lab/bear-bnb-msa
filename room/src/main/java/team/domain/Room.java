package team.domain;

import java.util.Date;
import java.util.List;
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

    @PostPersist
    public void onPostPersist() {
        RoomRegistered roomRegistered = new RoomRegistered(this);
        roomRegistered.publishAfterCommit();

        RoomAffirmed roomAffirmed = new RoomAffirmed(this);
        roomAffirmed.publishAfterCommit();

        RoomCancled roomCancled = new RoomCancled(this);
        roomCancled.publishAfterCommit();
    }

    @PreUpdate
    public void onPreUpdate() {
        RoomModified roomModified = new RoomModified(this);
        roomModified.publishAfterCommit();
    }

    @PreRemove
    public void onPreRemove() {
        RoomDeleted roomDeleted = new RoomDeleted(this);
        roomDeleted.publishAfterCommit();
    }

    public static RoomRepository repository() {
        RoomRepository roomRepository = RoomApplication.applicationContext.getBean(
            RoomRepository.class
        );
        return roomRepository;
    }

    public static void updateReviewCnt(ReviewRegistered reviewRegistered) {
        /** Example 1:  new item 
        Room room = new Room();
        repository().save(room);

        */

        /** Example 2:  finding and process
        
        repository().findById(reviewRegistered.get???()).ifPresent(room->{
            
            room // do something
            repository().save(room);


         });
        */

    }

    public static void updateReviewCnt(ReviewDeleted reviewDeleted) {
        /** Example 1:  new item 
        Room room = new Room();
        repository().save(room);

        */

        /** Example 2:  finding and process
        
        repository().findById(reviewDeleted.get???()).ifPresent(room->{
            
            room // do something
            repository().save(room);


         });
        */

    }

    public static void affirmRoom(Reserved reserved) {
        /** Example 1:  new item 
        Room room = new Room();
        repository().save(room);

        RoomAffirmed roomAffirmed = new RoomAffirmed(room);
        roomAffirmed.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(reserved.get???()).ifPresent(room->{
            
            room // do something
            repository().save(room);

            RoomAffirmed roomAffirmed = new RoomAffirmed(room);
            roomAffirmed.publishAfterCommit();

         });
        */

    }

    public static void cancelRoom(Canceled canceled) {
        /** Example 1:  new item 
        Room room = new Room();
        repository().save(room);

        RoomCancled roomCancled = new RoomCancled(room);
        roomCancled.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(canceled.get???()).ifPresent(room->{
            
            room // do something
            repository().save(room);

            RoomCancled roomCancled = new RoomCancled(room);
            roomCancled.publishAfterCommit();

         });
        */

    }
}
