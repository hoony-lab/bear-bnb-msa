package team.domain;

import team.domain.ReviewDeleted;
import team.domain.ReviewRegistered;
import team.RoomApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name="Review_table")
@Data

public class Review  {

    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long id;
    
    
    
    
    
    private Long roomId;
    
    
    
    
    
    private Long customerId;
    
    
    
    
    
    private String content;

    @PostPersist
    public void onPostPersist(){

        /*
         * 
         ReviewDeleted reviewDeleted = new ReviewDeleted(this);
         reviewDeleted.publishAfterCommit();
         */



        ReviewRegistered reviewRegistered = new ReviewRegistered(this);
        reviewRegistered.publishAfterCommit();

    }

    @PostRemove
    public void onPostRemove(){   
         ReviewDeleted reviewDeleted = new ReviewDeleted(this);
         reviewDeleted.publishAfterCommit();
    }
    
    @PreRemove
    public void onPreRemove(){
    }

    public static ReviewRepository repository(){
        ReviewRepository reviewRepository = RoomApplication.applicationContext.getBean(ReviewRepository.class);
        return reviewRepository;
    }






}
