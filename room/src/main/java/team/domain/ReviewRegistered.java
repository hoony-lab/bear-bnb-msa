package team.domain;

import java.util.*;
import lombok.*;
import team.domain.*;
import team.infra.AbstractEvent;

@Data
@ToString
public class ReviewRegistered extends AbstractEvent {

    private Long id;
    private Long roomId;
    private Long customerId;
    private String content;

    public ReviewRegistered(Review aggregate) {
        super(aggregate);
    }

    public ReviewRegistered() {
        super();
    }
    // keep

}
