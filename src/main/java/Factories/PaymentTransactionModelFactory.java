package Factories;

import Models.PaymentTransactionModel;

import java.util.Date;

public class PaymentTransactionModelFactory {

    public PaymentTransactionModel createPaymentTransactionModel(String cardNumber, String cvv,
                                                                 Date expirationDate,String amount,
                                                                 String usage, String transaction_type,
                                                                 String card_holder, String email,
                                                                 String address){

        PaymentTransactionModel payload = new PaymentTransactionModel();
        payload.setCard_number(cardNumber);
        payload.setCvv(cvv);
        payload.setExpiration_date(expirationDate);
        payload.setAmount(amount);
        payload.setUsage(usage);
        payload.setTransaction_type(transaction_type);
        payload.setCard_holder(card_holder);
        payload.setEmail(email);
        payload.setAddress(address);

        return payload;
    }
}
