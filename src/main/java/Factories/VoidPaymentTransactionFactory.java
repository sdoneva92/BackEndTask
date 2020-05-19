package Factories;

import Models.PaymentTransactionVoidTypeModel;
import Models.VoidPaymentTransaction;

public class VoidPaymentTransactionFactory {

    public VoidPaymentTransaction createVoidTransaction(PaymentTransactionVoidTypeModel transaction_type){

        VoidPaymentTransaction voidPaymentTransaction = new VoidPaymentTransaction();
        voidPaymentTransaction.setPayment_transaction(transaction_type);

        return voidPaymentTransaction;
    }
}
