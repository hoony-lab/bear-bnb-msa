package team.domain;

import java.util.*;
import lombok.*;
import team.domain.*;
import team.infra.AbstractEvent;

@Data
@ToString
public class ReviewDeleted extends AbstractEvent {

    private Long id;
    private Long roomId;
    private Long customerId;
    private String content;

    public ReviewDeleted(Review aggregate) {
        super(aggregate);
    }

    public ReviewDeleted() {
        super();
    }
    // keep

}
