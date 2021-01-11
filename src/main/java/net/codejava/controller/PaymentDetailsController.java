package net.codejava.controller;

import net.codejava.dto.PaymentDetailsDto;
import net.codejava.exception.PaymentDetailsNotFoundException;
import net.codejava.repository.PaymentDetailsRepository;
import net.codejava.service.PaymentDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentDetailsController {
    @Autowired
    private PaymentDetailsService paymentDetailsService;
    @Autowired
    private PaymentDetailsRepository repo;

    @GetMapping("paymentDetails")
    public List<PaymentDetailsDto> getAllPaymentDetails() throws PaymentDetailsNotFoundException { return paymentDetailsService.getAllPaymentDetails();}

    @GetMapping("paymentDetailsById")
    public PaymentDetailsDto getPaymentDetailsById(@RequestParam int paymentId) throws PaymentDetailsNotFoundException {
        return paymentDetailsService.getPaymentDetailsByPaymentId(paymentId);
    }

    @PostMapping("paymentDetails")
    public PaymentDetailsDto addPaymentDetails(@RequestBody PaymentDetailsDto paymentDetailsDto){
        return paymentDetailsService.addPaymentDetails(paymentDetailsDto);
    }

    @DeleteMapping("paymentDetails")
    public boolean deletePaymentDetailsByPaymentId(@RequestParam int paymentId) throws PaymentDetailsNotFoundException {
        return paymentDetailsService.deletePaymentDetailsByPaymentId(paymentId);
    }

    @PutMapping("paymentDetails")
    public PaymentDetailsDto updatePaymentDetails(@RequestParam int paymentId,@RequestBody PaymentDetailsDto paymentDetailsDto) throws PaymentDetailsNotFoundException {
        return paymentDetailsService.updatePaymentDetailsByDetailsId(paymentId, paymentDetailsDto);
    }
}
