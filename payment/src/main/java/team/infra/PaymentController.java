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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
// @RequestMapping(value="/payments")
@Transactional
public class PaymentController {

    @Autowired
    PaymentRepository paymentRepository;
    // keep

    @PostMapping(value="/payments")
    public String requestPayment(@RequestBody Payment entity) {
        String result = "//TODO: process POST request";
        try {
            Thread.currentThread().sleep((long) (400 + Math.random() * 220));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        ReservationRequested reservationRequested = new ReservationRequested();
        reservationRequested.setReserveStatus("예예약완료료");
        reservationRequested.setCustomerId(111L);
        // entity.affirmPayment(reservationRequested);
        System.out.println(result);
        return result;
    }
    
}
