package com.tysystems.pms.domain.dto.security;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.tysystems.pms.domain.dto.security.QLoginUsrInfoDto is a Querydsl Projection type for LoginUsrInfoDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QLoginUsrInfoDto extends ConstructorExpression<LoginUsrInfoDto> {

    private static final long serialVersionUID = 1205228989L;

    public QLoginUsrInfoDto(com.querydsl.core.types.Expression<String> usrId, com.querydsl.core.types.Expression<String> pjtId, com.querydsl.core.types.Expression<com.tysystems.pms.domain.entity.usr.Authority> authority) {
        super(LoginUsrInfoDto.class, new Class<?>[]{String.class, String.class, com.tysystems.pms.domain.entity.usr.Authority.class}, usrId, pjtId, authority);
    }

    public QLoginUsrInfoDto(com.querydsl.core.types.Expression<String> usrId, com.querydsl.core.types.Expression<String> password, com.querydsl.core.types.Expression<String> pjtId, com.querydsl.core.types.Expression<com.tysystems.pms.domain.entity.usr.Authority> authority) {
        super(LoginUsrInfoDto.class, new Class<?>[]{String.class, String.class, String.class, com.tysystems.pms.domain.entity.usr.Authority.class}, usrId, password, pjtId, authority);
    }

}

