package net.codejava.service.impl;

import net.codejava.dto.PaymentDetailsDto;
import net.codejava.dto.PropertyDto;
import net.codejava.model.PaymentDetails;
import net.codejava.model.Property;
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
    public List<PaymentDetailsDto> getAllPaymentDetails() {
        List<PaymentDetails> allPaymentDetails = paymentDetailsRepository.findAll();
        List<PaymentDetailsDto> paymentDetailsDtoList = new ArrayList<>();
        allPaymentDetails.forEach(paymentDetails -> paymentDetailsDtoList.add(PaymentDetailsDto.getPaymentDetailsDto(paymentDetails)));
        return paymentDetailsDtoList;
    }

    @Override
    public PaymentDetailsDto getPaymentDetailsByPaymentId(int paymentId) {
        Optional<PaymentDetails> optionPaymentDetails = paymentDetailsRepository.findById(paymentId);
        if (optionPaymentDetails.isEmpty()) return null;
        PaymentDetails tempPaymentDetails = optionPaymentDetails.get();
        return PaymentDetailsDto.getPaymentDetailsDto(tempPaymentDetails);
    }

    @Override
    public PaymentDetailsDto addPaymentDetails(PaymentDetailsDto paymentDetailsDto) {
        PaymentDetails paymentDetails = PaymentDetailsDto.getPaymentDetails(paymentDetailsDto);
        paymentDetailsRepository.save(paymentDetails);
        return PaymentDetailsDto.getPaymentDetailsDto(paymentDetails);
    }



    @Override
    public boolean deletePaymentDetailsByPaymentId(int paymentId) {
        Optional<PaymentDetails> optionPaymentDetails = paymentDetailsRepository.findById(paymentId);
        if(optionPaymentDetails.isEmpty()){
            return false;
        }
        PaymentDetails tempPaymentDetails = optionPaymentDetails.get();
        paymentDetailsRepository.deleteById(tempPaymentDetails.getPaymentId());
        return true;
    }

    @Override
    public PaymentDetailsDto updatePaymentDetailsByDetailsId(PaymentDetailsDto paymentDetailsDto, int paymentId) {
        Optional<PaymentDetails> optionPayment = paymentDetailsRepository.findById(paymentId);
        if(optionPayment.isEmpty()){
            return null;
        }

        PaymentDetails retPayment = optionPayment.get();
        retPayment.setPaymentDate(paymentDetailsDto.getPaymentDate());
        retPayment.setCardHolderName(paymentDetailsDto.getCardHolderName());
        retPayment.setCreditCardNo(paymentDetailsDto.getCreditCardNo());
        retPayment.setExpiryDate(paymentDetailsDto.getExpiryDate());
        retPayment.setCvv(paymentDetailsDto.getCvv());

        return PaymentDetailsDto.getPaymentDetailsDto(paymentDetailsRepository.save(retPayment));
    }
}
