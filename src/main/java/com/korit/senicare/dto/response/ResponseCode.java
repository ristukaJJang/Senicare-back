package com.korit.senicare.dto.response;

// Response DTO의 code 상수 선언

public interface ResponseCode {
    
    String SUCCESS = "SU";
    
    String VALIDATION_FAIL = "VF";
    String DUPLICATE_USER_ID = "DI";
    String DUPLICATE_TEL_NUMBER = "DT";
    String NO_EXIST_USER_ID = "NI";

    String TEL_AUTH_FAIL = "TAF";
    String SIGN_IN_FAIL = "SF";
    String AUTHENTICATION_FAIL = "AF";

    String DATABASE_ERROR = "DBE";
    String MESSAGE_SEND_FAIL = "TF";
    String TOKEN_CREATE_FAIL = "TCF";
}
