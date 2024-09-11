package com.korit.senicare.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.korit.senicare.common.util.AuthNumberCreator;
import com.korit.senicare.dto.request.auth.IdCheckRequestDto;
import com.korit.senicare.dto.request.auth.TelAuthCheckRequestDto;
import com.korit.senicare.dto.request.auth.TelAuthRequestDto;
import com.korit.senicare.dto.response.ResponseDto;
import com.korit.senicare.entity.TelAuthNumberEntity;
import com.korit.senicare.provider.SmsProvider;
import com.korit.senicare.repository.NurseRepository;
import com.korit.senicare.repository.TelAuthNumberRepository;
import com.korit.senicare.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService{
    
    private final NurseRepository nurseRepository;
    private final TelAuthNumberRepository telAuthNumberRepository;
    private final SmsProvider smsProvider;

    @Override
    public ResponseEntity<ResponseDto> idCheck(IdCheckRequestDto dto) {
        
        String userId = dto.getUserId();

        try {
            // 찾는 아이디 데이터가 필요하면 findBy, 조회만 하면 existBy 메서드 사용
            boolean isExistedId = nurseRepository.existsByUserId(userId);
            if(isExistedId) return ResponseDto.duplicatedUserId(); 
                
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> telAuth(TelAuthRequestDto dto) {

        String telNumber = dto.getTelNumber();

        // 동일 전화번호 조회
        try {
            boolean isExistedTelNumber = nurseRepository.existsByTelNumber(telNumber);
            if(isExistedTelNumber) return ResponseDto.duplicatedTelNumber(); 
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        
        String authNumber = AuthNumberCreator.number4();
        boolean isSendSuccess = smsProvider.sendMessage(telNumber, authNumber);
        if(!isSendSuccess) return ResponseDto.messageSendFail();

        // 인증번호 4자리 db에 저장
        try {
            TelAuthNumberEntity telAuthNumberEntity = new TelAuthNumberEntity(telNumber, authNumber);
            telAuthNumberRepository.save(telAuthNumberEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        
        return ResponseDto.success();
        
    }

    @Override
    public ResponseEntity<ResponseDto> telAuthCheck(TelAuthCheckRequestDto dto) {
        String telNumber = dto.getTelNumber();
        String authNumber = dto.getAuthNumber();

        try {
            boolean isMatched = telAuthNumberRepository.existsByTelNumberAndAuthNumber(telNumber, authNumber);
            if(!isMatched) return ResponseDto.telAuthFail();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }
    
}
