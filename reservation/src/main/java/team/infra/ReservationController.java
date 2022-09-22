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
@RequestMapping(value = "/reservations")
@Transactional
public class ReservationController {

    @Autowired
    ReservationRepository reservationRepository;

    // @RequestMapping(value = "reservations/{roomId}/requestReservation",
    // method = RequestMethod.PUT,
    // produces = "application/json;charset=UTF-8")
    // public Reservation requestReservation(@PathVariable(value = "roomId") Long roomId, HttpServletRequest request, HttpServletResponse response) throws Exception {
    //     System.out.println("##### /reservation/requestReservation  called #####");
    //     Reservation reservation = new Reservation();
    //     reservation.setRoomId(roomId);
    //     // reservation.requestReservation();
        
    //     reservationRepository.save(reservation);
    //     return reservation;
        
    // }
    // keep
}
