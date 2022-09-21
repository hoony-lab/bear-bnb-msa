package team.infra;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import team.domain.*;

@RepositoryRestResource(collectionResourceRel = "roomViews", path = "roomViews")
public interface RoomViewRepository
    extends PagingAndSortingRepository<RoomView, Long> {
    // keep

}
