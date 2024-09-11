package com.korit.senicare.dto.response;

// Response DTO의 code 상수 선언

public interface ResponseCode {
    
    String SUCCESS = "SU";
    
    String VALIDATION_FAIL = "VF";
    String DUPLICATE_USER_ID = "DI";
    String DUPLICATE_TEL_NUMBER = "DT";

    String TEL_AUTH_FAIL = "TAF";

    String DATABASE_ERROR = "DBE";
    String MESSAGE_SEND_FAIL = "TF";
}
