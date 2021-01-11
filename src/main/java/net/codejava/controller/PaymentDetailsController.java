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

    //http://localhost:9090/paymentDetails GET
    @GetMapping("paymentDetails")
    public List<PaymentDetailsDto> getAllPaymentDetails() throws PaymentDetailsNotFoundException { return paymentDetailsService.getAllPaymentDetails();}

    //http://localhost:9090/paymentDetails/paymentId GET
    @GetMapping("paymentDetailsById/{paymentId}")
    public PaymentDetailsDto getPaymentDetailsById(@PathVariable int paymentId) throws PaymentDetailsNotFoundException {
        return paymentDetailsService.getPaymentDetailsByPaymentId(paymentId);
    }

    //http://localhost:9090/paymentDetails POST
    @PostMapping("paymentDetails")
    public PaymentDetailsDto addPaymentDetails(@RequestBody PaymentDetailsDto paymentDetailsDto){
        return paymentDetailsService.addPaymentDetails(paymentDetailsDto);
    }

    //http://localhost:9090/paymentDetails/paymentId DELETE
    @DeleteMapping("paymentDetails/{paymentId}")
    public boolean deletePaymentDetailsByPaymentId(@PathVariable int paymentId) throws PaymentDetailsNotFoundException {
        return paymentDetailsService.deletePaymentDetailsByPaymentId(paymentId);
    }

    //http://localhost:9090/paymentDetails PUT
    @PutMapping("paymentDetails")
    public PaymentDetailsDto updatePaymentDetails(@RequestParam int paymentId,@RequestBody PaymentDetailsDto paymentDetailsDto) throws PaymentDetailsNotFoundException {
        return paymentDetailsService.updatePaymentDetailsByDetailsId(paymentId, paymentDetailsDto);
    }
}
