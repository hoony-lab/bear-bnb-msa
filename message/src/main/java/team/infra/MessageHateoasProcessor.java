package team.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import team.domain.*;

@Component
public class MessageHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Message>> {

    @Override
    public EntityModel<Message> process(EntityModel<Message> model) {
        return model;
    }
}
