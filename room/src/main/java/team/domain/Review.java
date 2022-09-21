package team.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import team.RoomApplication;
import team.domain.ReviewDeleted;
import team.domain.ReviewRegistered;

@Entity
@Table(name = "Review_table")
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long roomId;

    private Long customerId;

    private String content;

    @PostPersist
    public void onPostPersist() {
        ReviewDeleted reviewDeleted = new ReviewDeleted(this);
        reviewDeleted.publishAfterCommit();

        ReviewRegistered reviewRegistered = new ReviewRegistered(this);
        reviewRegistered.publishAfterCommit();
    }

    @PreRemove
    public void onPreRemove() {}

    public static ReviewRepository repository() {
        ReviewRepository reviewRepository = RoomApplication.applicationContext.getBean(
            ReviewRepository.class
        );
        return reviewRepository;
    }
}
