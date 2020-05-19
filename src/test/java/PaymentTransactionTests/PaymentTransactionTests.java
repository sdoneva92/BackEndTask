package PaymentTransactionTests;

import Factories.PaymentTransactionModelFactory;
import Factories.PaymentTransactionVoidTypeModelFactory;
import Factories.SalePaymentTransactionFactory;
import Factories.VoidPaymentTransactionFactory;
import Models.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static Constants.Constants.BASE_PATH_PAYMENT_TRANSACTION_ENDPOINT;
import static Constants.Constants.BASE_PATH_VOID_PAYMENT_TRANSACTION;

public class PaymentTransactionTests {

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

        PaymentTransactionModel paymentTransactionModel = new PaymentTransactionModelFactory().createPaymentTransactionModel(cardNumber, cvv,
                expirationDate, amount, usage, transactionType, cardHolder, email, address);

        SalePaymentTrasactionModel salePaymentTrasactionModel = new SalePaymentTransactionFactory().createSaleTransaction(paymentTransactionModel);
        RestAssured.given()
                .when()
                .header("Authorization", "Basic Y29kZW1vbnN0ZXI6bXk1ZWNyZXQta2V5Mm8ybw==")
                .contentType(ContentType.JSON)
                .when()
                .body(salePaymentTrasactionModel)
                .post(BASE_PATH_PAYMENT_TRANSACTION_ENDPOINT)
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200);

    }

    @Test
    public void validatePaymentVoidTransaction() {

        PaymentTransactionVoidTypeModel paymentTransactionVoidTypeModel = new PaymentTransactionVoidTypeModelFactory().createPaymentTransactionVoidTypeModel("0e08644635ccb520c2eeb54f33865660","void");
        VoidPaymentTransaction voidPaymentTransaction = new VoidPaymentTransactionFactory().createVoidTransaction(paymentTransactionVoidTypeModel);
        RestAssured.given()
                .when()
                .header("Authorization", "Basic Y29kZW1vbnN0ZXI6bXk1ZWNyZXQta2V5Mm8ybw==")
                .contentType(ContentType.JSON)
                .when()
                .body(voidPaymentTransaction)
                .post(BASE_PATH_VOID_PAYMENT_TRANSACTION)
                .then()
                .contentType(ContentType.JSON)
                .statusCode(201);
    }
}
