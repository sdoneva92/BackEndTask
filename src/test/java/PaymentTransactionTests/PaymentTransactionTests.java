package PaymentTransactionTests;

import Models.ErrorModel;
import Models.SuccessResponseModel;
import Steps.TransactionSteps;
import org.assertj.core.api.BDDSoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

public class PaymentTransactionTests {

      BDDSoftAssertions softly = new BDDSoftAssertions();

    @Test
    public void validatePaymentSaleTransaction() {

        String cardNumber = "4200000000000000";
        String cvv = "123";
        Date expirationDate = new Date(2019, Calendar.JULY, 0);
        String amount = "500";
        String usage = "Coffeemaker";
        String transactionType = "sale";
        String cardHolder = "Panda Panda";
        String email = "panda@example.com";
        String address = "Panda Street, China";

        TransactionSteps steps = new TransactionSteps();
        SuccessResponseModel successResponseModel = steps.sendPostRequestForSaleTransaction(cardNumber, cvv, expirationDate, amount, usage, transactionType, cardHolder, email, address);
        String requestId = successResponseModel.getUnique_id();

        SuccessResponseModel voidSuccessResponseModel = steps.sendPostRequestForVoidTransaction(requestId, "void");

        softly.then(voidSuccessResponseModel.getStatus()).isEqualTo("approved");
        softly.then(voidSuccessResponseModel.getAmount()).isEqualTo(amount);
        softly.assertAll();
    }


    @Test
    public void validatePaymentSaleTransactionWithError() {

        TransactionSteps steps = new TransactionSteps();
        ErrorModel errorModel = steps.sendPostRequestForVoidTransactionWithError("123456789", "void", 422);
        //softly.then(errorModel.getReference_id()).isEqualTo("Invalid reference transaction!");
    }
}
