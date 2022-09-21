package team.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import team.MessageApplication;

@Entity
@Table(name = "Message_table")
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer reservationId;

    private Date messageDate;

    private String messageContents;

    private String messageStatus;

    public static MessageRepository repository() {
        MessageRepository messageRepository = MessageApplication.applicationContext.getBean(
            MessageRepository.class
        );
        return messageRepository;
    }

    public static void sendReservationMessage(Reserved reserved) {
        /** Example 1:  new item 
        Message message = new Message();
        repository().save(message);

        */

        /** Example 2:  finding and process
        
        repository().findById(reserved.get???()).ifPresent(message->{
            
            message // do something
            repository().save(message);


         });
        */

    }

    public static void sendCancelReservationMessage(Canceled canceled) {
        /** Example 1:  new item 
        Message message = new Message();
        repository().save(message);

        */

        /** Example 2:  finding and process
        
        repository().findById(canceled.get???()).ifPresent(message->{
            
            message // do something
            repository().save(message);


         });
        */

    }
}
