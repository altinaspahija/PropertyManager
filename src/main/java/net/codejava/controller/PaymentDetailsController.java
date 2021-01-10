package net.codejava.controller;

import net.codejava.dto.PaymentDetailsDto;
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

    @GetMapping("getAllPaymentDetails")
    public List<PaymentDetailsDto> getAllPaymentDetails() { return paymentDetailsService.getAllPaymentDetails();}

    @GetMapping("getPaymentDetailsById")
    public PaymentDetailsDto getPaymentDetailsById(@RequestParam int paymentId){
        PaymentDetailsDto paymentDetails = paymentDetailsService.getPaymentDetailsByPaymentId(paymentId);
        if (paymentDetails == null){
            return null;
        }
        else{
            return paymentDetails;
        }
    }

    @PostMapping("addPaymentDetails")
    public PaymentDetailsDto addPaymentDetails(@RequestBody PaymentDetailsDto paymentDetailsDto){
        return paymentDetailsService.addPaymentDetails(paymentDetailsDto);
    }

    @DeleteMapping("paymentDetails")
    public boolean deletePaymentDetailsByPaymentId(@RequestParam int paymentId){
        return paymentDetailsService.deletePaymentDetailsByPaymentId(paymentId);
    }

    @PutMapping("/paymentDetails")
    public PaymentDetailsDto updatePaymentDetails(@RequestParam int paymentId, @RequestBody PaymentDetailsDto paymentDetailsDto){
        return paymentDetailsService.updatePaymentDetailsByDetailsId(paymentDetailsDto, paymentId);
    }
}
