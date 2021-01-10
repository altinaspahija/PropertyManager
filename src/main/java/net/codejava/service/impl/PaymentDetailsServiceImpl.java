package net.codejava.service.impl;

import net.codejava.dto.PaymentDetailsDto;
import net.codejava.dto.PropertyDto;
import net.codejava.model.PaymentDetails;
import net.codejava.model.Property;
import net.codejava.repository.PaymentDetailsRepository;
import net.codejava.service.PaymentDetailsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class PaymentDetailsServiceImpl implements PaymentDetailsService {

    @Autowired
    private PaymentDetailsRepository paymentDetailsRepository;

    @Override
    public List<PaymentDetailsDto> getPaymentDetailsByReservationId(Integer reservationId) {
        List<PaymentDetails> paymentDetails = paymentDetailsRepository.getPaymentDetailsByUserId(reservationId);
        List<PaymentDetailsDto> paymentDetailsDtos = new ArrayList<>();

        paymentDetails.forEach(payment -> paymentDetailsDtos.add(PaymentDetailsDto.getPaymentDetailsDto(payment)));
        return paymentDetailsDtos;

    }

    @Override
    public List<PaymentDetailsDto> getPaymentDetailsByUserId(Integer userId) {
        List<PaymentDetails> paymentDetails = paymentDetailsRepository.getPaymentDetailsByUserId(userId);
        List<PaymentDetailsDto> paymentDetailsDtos = new ArrayList<>();

        paymentDetails.forEach(payment -> paymentDetailsDtos.add(PaymentDetailsDto.getPaymentDetailsDto(payment)));
        return paymentDetailsDtos;
    }

    @Override
    public PaymentDetailsDto addPaymentDetails(PaymentDetailsDto paymentDetailsDto) {
        PaymentDetails paymentDetails = PaymentDetailsDto.getPaymentDetails(paymentDetailsDto);
        return PaymentDetailsDto.getPaymentDetailsDto(paymentDetailsRepository.addPaymentDetails(
                paymentDetails.getPaymentId(),
                paymentDetails.getReservationId(),
                paymentDetails.getPaymentDate(),
                paymentDetails.getCardHolderName(),
                paymentDetails.getCreditCardNo(),
                paymentDetails.getExpiryDate(),
                paymentDetails.getCsv()));
    }
}
