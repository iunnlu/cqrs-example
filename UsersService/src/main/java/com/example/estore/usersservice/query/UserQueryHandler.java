package com.example.estore.usersservice.query;

import com.example.estore.core.model.PaymentDetails;
import com.example.estore.core.model.User;
import com.example.estore.core.query.FetchUserPaymentDetailsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class UserQueryHandler {
    @QueryHandler
    public User findUser(FetchUserPaymentDetailsQuery fetchUserPaymentDetailsQuery) {
        PaymentDetails paymentDetails = PaymentDetails.builder()
                .cardNumber("card123")
                .cvv("432")
                .name("ILHAN UNLU")
                .validUntilMonth(12)
                .validUntilYear(2030)
                .build();
        User userRest = User.builder()
                .firstName("İlhan")
                .lastName("Ünlü")
                .userId(fetchUserPaymentDetailsQuery.getUserId())
                .paymentDetails(paymentDetails)
                .build();

        return userRest;
    }
}
