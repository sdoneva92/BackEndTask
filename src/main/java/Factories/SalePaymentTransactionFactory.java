package Factories;

import Models.PaymentTransactionModel;
import Models.PaymentTransactionVoidTypeModel;
import Models.SalePaymentTrasactionModel;
import Models.VoidPaymentTransaction;

import java.util.Date;

public class SalePaymentTransactionFactory {

    public SalePaymentTrasactionModel createSaleTransaction(PaymentTransactionModel paymentTransactionModel) {

        SalePaymentTrasactionModel salePaymentTrasactionModel = new SalePaymentTrasactionModel();
        salePaymentTrasactionModel.setPayment_transaction(paymentTransactionModel);

        return salePaymentTrasactionModel;
    }

    public VoidPaymentTransaction createVoidPaymentTransaction(PaymentTransactionVoidTypeModel paymentTransactionVoidTypeModel){

        VoidPaymentTransaction voidPaymentTransaction = new VoidPaymentTransaction();
        voidPaymentTransaction.setPayment_transaction(paymentTransactionVoidTypeModel);

        return voidPaymentTransaction;
    }
}
