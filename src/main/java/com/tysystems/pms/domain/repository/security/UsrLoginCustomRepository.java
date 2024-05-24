package com.tysystems.pms.domain.repository.security;

import com.tysystems.pms.domain.dto.security.LoginUsrAuthDto;
import com.tysystems.pms.domain.dto.security.LoginUsrInfoDto;
import com.tysystems.pms.web.form.security.LoginRequestForm;

import java.util.List;

public interface UsrLoginCustomRepository {
    List<LoginUsrInfoDto> loginUsrSearch(LoginRequestForm form);

    List<LoginUsrAuthDto> selectLoginUsrAuth(LoginRequestForm form);
}
