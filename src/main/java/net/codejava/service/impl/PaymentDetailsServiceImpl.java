package net.codejava.service.impl;

import net.codejava.dto.PaymentDetailsDto;
import net.codejava.dto.ReservationDto;
import net.codejava.exception.PaymentDetailsNotFoundException;
import net.codejava.model.PaymentDetails;
import net.codejava.model.Reservation;
import net.codejava.repository.PaymentDetailsRepository;
import net.codejava.service.PaymentDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentDetailsServiceImpl implements PaymentDetailsService {

    @Autowired
    private PaymentDetailsRepository paymentDetailsRepository;

    @Override
    public List<PaymentDetailsDto> getAllPaymentDetails() throws PaymentDetailsNotFoundException {
        List<PaymentDetails> allPaymentDetails = paymentDetailsRepository.findAll();
        List<PaymentDetailsDto> paymentDetailsDtoList = new ArrayList<>();
        if(allPaymentDetails.isEmpty()) throw new PaymentDetailsNotFoundException();
        allPaymentDetails.forEach(paymentDetails -> paymentDetailsDtoList.add(PaymentDetailsDto.getPaymentDetailsDto(paymentDetails)));
        return paymentDetailsDtoList;
    }

    @Override
    public PaymentDetailsDto getPaymentDetailsByPaymentId(int paymentId) throws PaymentDetailsNotFoundException {
        Optional<PaymentDetails> optionPaymentDetails = paymentDetailsRepository.findById(paymentId);
        if (optionPaymentDetails.isEmpty()) throw new PaymentDetailsNotFoundException();
        PaymentDetails tempPaymentDetails = optionPaymentDetails.get();
        return PaymentDetailsDto.getPaymentDetailsDto(tempPaymentDetails);
    }

    @Override
    public PaymentDetailsDto addPaymentDetails(PaymentDetailsDto paymentDetailsDto) {
        PaymentDetails paymentDetails = PaymentDetailsDto.getPaymentDetails(paymentDetailsDto);

        //get current date in sql Date form
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);

        //check the expiration date of the card and then save the payment
        if (paymentDetailsDto.getExpiryDate().after(date)) {
            paymentDetailsRepository.save(paymentDetails);}

        return PaymentDetailsDto.getPaymentDetailsDto(paymentDetails);
    }

    @Override
    public boolean deletePaymentDetailsByPaymentId(int paymentId) throws PaymentDetailsNotFoundException {
        Optional<PaymentDetails> optionPaymentDetails = paymentDetailsRepository.findById(paymentId);
        if(optionPaymentDetails.isEmpty()){
            throw new PaymentDetailsNotFoundException();
        }
        PaymentDetails tempPaymentDetails = optionPaymentDetails.get();
        paymentDetailsRepository.deleteById(tempPaymentDetails.getPaymentId());
        return true;
    }

    @Override
    public PaymentDetailsDto updatePaymentDetailsByDetailsId(int paymentId, PaymentDetailsDto paymentDetailsDto) throws PaymentDetailsNotFoundException {
            Optional<PaymentDetails> optionPaymentDetails = paymentDetailsRepository.findById(paymentId);
            if (optionPaymentDetails.isEmpty()) throw new PaymentDetailsNotFoundException();
            PaymentDetails paymentDetails = optionPaymentDetails.get();
            paymentDetails.setCardHolderName(paymentDetailsDto.getCardHolderName());
            paymentDetails.setCreditCardNo(paymentDetailsDto.getCreditCardNo());
            paymentDetails.setPaymentDate(paymentDetailsDto.getPaymentDate());
            paymentDetails.setExpiryDate(paymentDetailsDto.getExpiryDate());
            paymentDetails.setCvv(paymentDetailsDto.getCvv());

            paymentDetailsRepository.save(paymentDetails);
            return PaymentDetailsDto.getPaymentDetailsDto(paymentDetails);
    }
}
