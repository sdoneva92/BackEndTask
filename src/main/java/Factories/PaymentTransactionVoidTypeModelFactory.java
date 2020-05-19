package Factories;

import Models.PaymentTransactionVoidTypeModel;

public class PaymentTransactionVoidTypeModelFactory {

    public PaymentTransactionVoidTypeModel createPaymentTransactionVoidTypeModel(String reference_id, String transaction_type){

        PaymentTransactionVoidTypeModel payload = new PaymentTransactionVoidTypeModel();
        payload.setReference_id(reference_id);
        payload.setTransaction_type(transaction_type);

        return  payload;
    }
}
