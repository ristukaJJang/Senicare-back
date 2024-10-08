package com.korit.senicare.dto.response;

// ResponseDTO의 message 상수 선언

public interface ResponseMessage {
    String SUCCESS = "Success";

    String DUPLICATE_USER_ID = "Duplicated User Id";
    String VALIDATION_FAIL = "Validation Failed";
    String DUPLICATE_TEL_NUMBER = "Duplicated User Tel Number";
    String NO_EXIST_USER_ID = "No Exist User Id";
    String NO_EXIST_TOOL = "No Exist Tool";
    String NO_EXIST_CUSTOMER = "No Exist Customer";

    String TEL_AUTH_FAIL = "Telnumber Authentication Failed";
    String SIGN_IN_FAIL = "Sign in Failed";
    String AUTHENTICATION_FAIL = "Authentication Fail";
    String TOOL_INSUFFICIENT = "This tool is insufficient in number.";

    String NO_PERMISSION = "No Permission";

    String MESSAGE_SEND_FAIL = "Auth Number Send Failed";
    String DATABASE_ERROR = "Database Error";
    String TOKEN_CREATE_FAIL = "Token Create Failed";
}
