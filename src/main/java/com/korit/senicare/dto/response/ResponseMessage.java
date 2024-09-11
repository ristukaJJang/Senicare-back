package com.korit.senicare.dto.response;

// ResponseDTO의 message 상수 선언

public interface ResponseMessage {
    String SUCCESS = "Success";

    String DUPLICATE_USER_ID = "Duplicated User Id";
    String VALIDATION_FAIL = "Validation Failed";
    String DUPLICATE_TEL_NUMBER = "Duplicated User Tel Number";

    String TEL_AUTH_FAIL = "Telnumber Authentication Failed";

    String MESSAGE_SEND_FAIL = "Auth Number Send Failed";
    String DATABASE_ERROR = "Database Error";
}
