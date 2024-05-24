package com.tysystems.pms.domain.dto.security;

import lombok.Getter;

@Getter
public class LoginUsrAuthDto {
    private String usrId;
    private String Authority;

    public LoginUsrAuthDto(String usrId, String authority) {
        this.usrId = usrId;
        Authority = authority;
    }
}
