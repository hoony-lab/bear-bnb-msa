package team.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.domain.*;

@RestController
// @RequestMapping(value="/rooms")
@Transactional
public class RoomController {

    @Autowired
    RoomRepository roomRepository;

    @RequestMapping(
        value = "rooms/{id}/register room",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Room registerRoom(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /room/registerRoom  called #####");
        Optional<Room> optionalRoom = roomRepository.findById(id);

        optionalRoom.orElseThrow(() -> new Exception("No Entity Found"));
        Room room = optionalRoom.get();
        room.registerRoom();

        roomRepository.save(room);
        return room;
    }
    // keep
}
