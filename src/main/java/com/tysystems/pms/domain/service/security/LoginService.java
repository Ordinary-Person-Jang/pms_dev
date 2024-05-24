package com.tysystems.pms.domain.service.security;

import com.tysystems.pms.domain.dto.security.LoginUsrAuthDto;
import com.tysystems.pms.domain.repository.usr.UsrRepository;
import com.tysystems.pms.web.form.security.LoginRequestForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UsrRepository usrRepository;

    public List<LoginUsrAuthDto> setLoginUsrAuth(LoginRequestForm from){
        return usrRepository.selectLoginUsrAuth(from);
    }
}
