package team.infra;

import team.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

@RepositoryRestResource(collectionResourceRel="roomViews", path="roomViews")
public interface RoomViewRepository extends PagingAndSortingRepository<RoomView, Long> {

    

    

    // keep

}
