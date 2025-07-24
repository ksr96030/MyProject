package com.santhosh.myproject.controller;

import com.santhosh.myproject.DTO.PaymentRequest;
import com.santhosh.myproject.model.PaymentDetails;
import com.santhosh.myproject.model.TransactionDetails;
import com.santhosh.myproject.repository.PaymentDetailsRepository;
import com.santhosh.myproject.repository.TransactionDetailsRepository;
import com.stripe.exception.CardException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentDetailsRepository paymentDetailsRepository;

    @Autowired
    private TransactionDetailsRepository transactionDetailsRepository;

    @PostMapping("/charge")
    public ResponseEntity<?> chargeCard(@RequestBody PaymentRequest request) {
        try {
            // Save basic payment info
            PaymentDetails paymentDetails = new PaymentDetails();
            paymentDetails.setCardHolderName(request.getCardHolderName());
            paymentDetails.setCardNumber(request.getCardNumber());
            paymentDetails.setCardType(request.getCardType());
            paymentDetails.setBillingAddress(request.getBillingAddress());
            paymentDetails.setEmail(request.getEmail());
            paymentDetails.setPhoneNumber(request.getPhoneNumber());
            paymentDetails.setAmount(request.getAmount());
            paymentDetails.setCurrency(request.getCurrency());
            paymentDetails.setPaymentMethod(request.getPaymentMethod());
            paymentDetails.setCustomerId(request.getCustomerId());
            paymentDetails.setCreatedDate(LocalDate.now());
            paymentDetailsRepository.save(paymentDetails);

            // Create charge with Stripe
            Map<String, Object> params = new HashMap<>();
            params.put("amount", (int) (request.getAmount() * 100));
            params.put("currency", request.getCurrency());
            params.put("source", request.getToken());
            params.put("description", "Payment from customerId: " + request.getCustomerId());

            Charge charge = Charge.create(params);

            // Save transaction
            TransactionDetails transaction = new TransactionDetails();
            transaction.setPaymentId(paymentDetails.getPaymentId());
            transaction.setPaymentStatus(charge.getStatus());
            transaction.setPaymentTime(LocalDateTime.now());
            transaction.setGatewayResponse(charge.toJson());
            transaction.setAmount(request.getAmount());
            transaction.setCurrency(request.getCurrency());
            transaction.setCustomerId(request.getCustomerId());
            transactionDetailsRepository.save(transaction);

            return ResponseEntity.ok("Payment Successful");

        } catch (CardException e) {
            // Handle card errors
            TransactionDetails failedTransaction = new TransactionDetails();
            failedTransaction.setPaymentStatus("failed");
            failedTransaction.setPaymentTime(LocalDateTime.now());
            failedTransaction.setGatewayResponse(e.getMessage());
            failedTransaction.setCurrency(request.getCurrency());
            failedTransaction.setAmount(request.getAmount());
            failedTransaction.setCustomerId(request.getCustomerId());
            failedTransaction.setErrorCode(e.getCode());
            failedTransaction.setErrorDescription(e.getDeclineCode());
            transactionDetailsRepository.save(failedTransaction);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payment Failed: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server Error");
        }
    }
}

