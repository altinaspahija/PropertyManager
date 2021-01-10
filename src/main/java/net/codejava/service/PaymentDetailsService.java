package net.codejava.service;

import net.codejava.dto.PaymentDetailsDto;
import net.codejava.dto.PropertyDto;

import java.util.List;

public interface PaymentDetailsService {
    List<PaymentDetailsDto> getAllPaymentDetails();
    PaymentDetailsDto getPaymentDetailsByPaymentId(int paymentId);
    PaymentDetailsDto addPaymentDetails(PaymentDetailsDto paymentDetailsDto);
    boolean deletePaymentDetailsByPaymentId(int paymentId);
    PaymentDetailsDto updatePaymentDetailsByDetailsId(PaymentDetailsDto paymentDetailsDto, int paymentId);
}
