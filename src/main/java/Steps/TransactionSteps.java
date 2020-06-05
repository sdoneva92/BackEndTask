package Steps;

import Factories.PaymentTransactionModelFactory;
import Factories.PaymentTransactionVoidTypeModelFactory;
import Factories.SalePaymentTransactionFactory;
import Factories.VoidPaymentTransactionFactory;
import Models.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import java.util.Date;

import static Constants.Constants.BASE_PATH_PAYMENT_TRANSACTION_ENDPOINT;
import static Constants.Constants.BASE_PATH_VOID_PAYMENT_TRANSACTION;

public class TransactionSteps {

    public SuccessResponseModel sendPostRequestForSaleTransaction(String cardNumber, String cvv, Date expirationDate, String amount,
                                                                  String usage, String transactionType, String cardHolder, String email,
                                                                  String address){

        PaymentTransactionModel paymentTransactionModel = new PaymentTransactionModelFactory().createPaymentTransactionModel(cardNumber, cvv,
                expirationDate, amount, usage, transactionType, cardHolder, email, address);

        SalePaymentTrasactionModel salePaymentTrasactionModel = new SalePaymentTransactionFactory().createSaleTransaction(paymentTransactionModel);

        RequestSpecification requestSpecification = RestAssured.given();

        requestSpecification.header("Authorization","Basic Y29kZW1vbnN0ZXI6bXk1ZWNyZXQta2V5Mm8ybw==");
        requestSpecification.body(salePaymentTrasactionModel);
        Response response =  requestSpecification.post(BASE_PATH_PAYMENT_TRANSACTION_ENDPOINT);
        response.then().statusCode(200);

        ResponseBody responseBody = response.getBody();

        return responseBody.as(SuccessResponseModel.class);
    }


    public SuccessResponseModel sendPostRequestForVoidTransaction(String referenceId, String transactionType){

        PaymentTransactionVoidTypeModel paymentTransactionVoidTypeModel = new PaymentTransactionVoidTypeModelFactory().createPaymentTransactionVoidTypeModel(referenceId,transactionType);
        VoidPaymentTransaction voidPaymentTransaction = new VoidPaymentTransactionFactory().createVoidTransaction(paymentTransactionVoidTypeModel);

        RequestSpecification requestSpecification = RestAssured.given();

        requestSpecification.header("Authorization","Basic Y29kZW1vbnN0ZXI6bXk1ZWNyZXQta2V5Mm8ybw==");
        requestSpecification.body(voidPaymentTransaction);
        Response response =  requestSpecification.post(BASE_PATH_VOID_PAYMENT_TRANSACTION);
        response.then().statusCode(201);

        ResponseBody responseBody = response.getBody();

        return responseBody.as(SuccessResponseModel.class);
    }

    public ErrorModel sendPostRequestForVoidTransactionWithError(String referenceId, String transactionType, int statusCode){

        PaymentTransactionVoidTypeModel paymentTransactionVoidTypeModel = new PaymentTransactionVoidTypeModelFactory().createPaymentTransactionVoidTypeModel(referenceId,transactionType);
        VoidPaymentTransaction voidPaymentTransaction = new VoidPaymentTransactionFactory().createVoidTransaction(paymentTransactionVoidTypeModel);

        RequestSpecification requestSpecification = RestAssured.given();

        requestSpecification.header("Authorization","Basic Y29kZW1vbnN0ZXI6bXk1ZWNyZXQta2V5Mm8ybw==");
        requestSpecification.body(voidPaymentTransaction);
        Response response =  requestSpecification.post(BASE_PATH_VOID_PAYMENT_TRANSACTION);
        response.then().statusCode(statusCode);

        ResponseBody responseBody = response.getBody();

        return responseBody.as( ErrorModel.class);
    }
}
