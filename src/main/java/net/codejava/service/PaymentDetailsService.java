package net.codejava.service;

import net.codejava.dto.PaymentDetailsDto;
import net.codejava.exception.PaymentDetailsNotFoundException;

import java.util.List;

public interface PaymentDetailsService {
    List<PaymentDetailsDto> getAllPaymentDetails() throws PaymentDetailsNotFoundException;
    PaymentDetailsDto getPaymentDetailsByPaymentId(int paymentId) throws PaymentDetailsNotFoundException;
    PaymentDetailsDto addPaymentDetails(PaymentDetailsDto paymentDetailsDto);
    boolean deletePaymentDetailsByPaymentId(int paymentId) throws PaymentDetailsNotFoundException;
    PaymentDetailsDto updatePaymentDetailsByDetailsId(int paymentId,PaymentDetailsDto paymentDetailsDto) throws PaymentDetailsNotFoundException;
}
