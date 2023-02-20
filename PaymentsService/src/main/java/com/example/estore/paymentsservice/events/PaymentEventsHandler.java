package com.example.estore.paymentsservice.events;

import com.example.estore.core.events.PaymentProcessedEvent;
import com.example.estore.paymentsservice.core.PaymentEntity;
import com.example.estore.paymentsservice.core.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentEventsHandler {
    private final Logger LOGGER = LoggerFactory.getLogger(PaymentEventsHandler.class);
    private final PaymentRepository paymentRepository;

    @EventHandler
    public void on(PaymentProcessedEvent paymentProcessedEvent) {
        LOGGER.info("PaymentProcessedEvent is called for orderId: " + paymentProcessedEvent.getOrderId());

        PaymentEntity paymentEntity = new PaymentEntity();
        BeanUtils.copyProperties(paymentProcessedEvent, paymentEntity);

        paymentRepository.save(paymentEntity);
    }
}
