package net.codejava.dto;

import lombok.Data;
import net.codejava.model.PaymentDetails;
import java.sql.Date;

@Data
public class PaymentDetailsDto {

    private int paymentId;
    private int reservationId;
    private Date paymentDate;
    private String cardHolderName;
    private long creditCardNo;
    private Date expiryDate;
    private int cvv;

    public static PaymentDetails getPaymentDetails(PaymentDetailsDto paymentDetailsDto) {
        if (paymentDetailsDto==null) return null;
        PaymentDetails paymentDetails = new PaymentDetails();

        paymentDetails.setPaymentId(paymentDetailsDto.getPaymentId());
        paymentDetails.setCardHolderName(paymentDetailsDto.getCardHolderName());
        paymentDetails.setCreditCardNo(paymentDetailsDto.getCreditCardNo());
        paymentDetails.setExpiryDate(paymentDetailsDto.getExpiryDate());
        paymentDetails.setPaymentDate(paymentDetailsDto.getPaymentDate());
        paymentDetails.setCvv(paymentDetailsDto.getCvv());

        return paymentDetails;
    }

    public static PaymentDetailsDto getPaymentDetailsDto(PaymentDetails paymentDetails) {
        if (paymentDetails==null) return null;
        PaymentDetailsDto paymentDetailsDto = new PaymentDetailsDto();

        paymentDetailsDto.setPaymentId(paymentDetails.getPaymentId());
        paymentDetailsDto.setCardHolderName(paymentDetails.getCardHolderName());
        paymentDetailsDto.setCreditCardNo(paymentDetails.getCreditCardNo());
        paymentDetailsDto.setPaymentDate(paymentDetails.getPaymentDate());
        paymentDetailsDto.setExpiryDate(paymentDetails.getExpiryDate());
        paymentDetailsDto.setCvv(paymentDetails.getCvv());

        return paymentDetailsDto;
    }
}
