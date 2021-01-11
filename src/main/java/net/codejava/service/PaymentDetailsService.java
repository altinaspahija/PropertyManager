package net.codejava.service;

import net.codejava.dto.PaymentDetailsDto;

import java.util.List;

public interface PaymentDetailsService {
    List<PaymentDetailsDto> getAllPaymentDetails();
    PaymentDetailsDto getPaymentDetailsByPaymentId(int paymentId);
    PaymentDetailsDto addPaymentDetails(PaymentDetailsDto paymentDetailsDto);
    boolean deletePaymentDetailsByPaymentId(int paymentId);
    PaymentDetailsDto updatePaymentDetailsByDetailsId(PaymentDetailsDto paymentDetailsDto);
}
