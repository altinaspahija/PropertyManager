package net.codejava.dto;

import lombok.Data;
import net.codejava.model.PaymentDetails;

import javax.persistence.Column;
import java.util.Date;

@Data
public class PaymentDetailsDto {

    private int paymentId;
    private int reservationId;
    private Date paymentDate;
    private String cardHolderName;
    private long creditCardNo;
    private Date expDate;
    private int csv;

    public static PaymentDetails getPaymentDetails(PaymentDetailsDto paymentDetailsDto) {
        if (paymentDetailsDto==null) return null;
        PaymentDetails paymentDetails = new PaymentDetails();

        paymentDetails.setPaymentId(paymentDetailsDto.getPaymentId());
        paymentDetails.setCardHolderName(paymentDetailsDto.getCardHolderName());
        paymentDetails.setCreditCardNo(paymentDetailsDto.getCreditCardNo());
        paymentDetails.setExpDate(paymentDetailsDto.getExpDate());
        paymentDetails.setPaymentDate(paymentDetailsDto.getPaymentDate());
        paymentDetails.setReservationId(paymentDetailsDto.getReservationId());
        paymentDetails.setCsv(paymentDetailsDto.getCsv());

        return paymentDetails;
    }

    public static PaymentDetailsDto getPaymentDetailsDto(PaymentDetails paymentDetails) {
        if (paymentDetails==null) return null;
        PaymentDetailsDto paymentDetailsDto = new PaymentDetailsDto();

        paymentDetailsDto.setPaymentId(paymentDetails.getPaymentId());
        paymentDetailsDto.setCardHolderName(paymentDetails.getCardHolderName());
        paymentDetailsDto.setCreditCardNo(paymentDetails.getCreditCardNo());
        paymentDetailsDto.setPaymentDate(paymentDetails.getPaymentDate());
        paymentDetailsDto.setReservationId(paymentDetails.getReservationId());
        paymentDetailsDto.setExpDate(paymentDetails.getExpDate());
        paymentDetailsDto.setCsv(paymentDetails.getCsv());

        return paymentDetailsDto;
    }
}
