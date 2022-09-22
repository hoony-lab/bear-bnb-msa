package team.external;

import org.springframework.stereotype.Component;

@Component
public class PaymentServiceFallback implements PaymentService {

    @Override
    public String requestPayment(Payment payment) {
        System.out.println(payment.toString());
        System.out.println("### PaymentServiceFallback");
        return "### PaymentServiceFallback";
    }
}