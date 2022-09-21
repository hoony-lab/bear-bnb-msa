package team.infra;
import team.domain.*;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import org.springframework.hateoas.EntityModel;

@Component
public class RoomHateoasProcessor implements RepresentationModelProcessor<EntityModel<Room>>  {

    @Override
    public EntityModel<Room> process(EntityModel<Room> model) {

        
        return model;
    }
    
}
