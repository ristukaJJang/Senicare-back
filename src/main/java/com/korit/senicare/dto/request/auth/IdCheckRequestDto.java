package com.korit.senicare.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 아이디 중복 확인 request body DTO
// 헤더에 들어갈 내용을 선언하는 공간임(DTO가)

@Getter
@Setter
@NoArgsConstructor
public class IdCheckRequestDto {

    @NotBlank
    private String userId;
}
