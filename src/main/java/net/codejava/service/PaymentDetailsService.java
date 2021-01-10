package net.codejava.service;

import net.codejava.dto.PaymentDetailsDto;
import net.codejava.dto.PropertyDto;

import java.util.List;

public interface PaymentDetailsService {
    List<PaymentDetailsDto> getPaymentDetailsByReservationId(Integer reservationId);

    List<PaymentDetailsDto> getPaymentDetailsByUserId(Integer userId);

    PaymentDetailsDto addPaymentDetails(PaymentDetailsDto paymentDetailsDto);
}
