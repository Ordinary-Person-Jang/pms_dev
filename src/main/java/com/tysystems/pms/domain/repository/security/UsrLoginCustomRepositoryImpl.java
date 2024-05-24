package com.tysystems.pms.domain.repository.security;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tysystems.pms.domain.dto.security.LoginUsrAuthDto;
import com.tysystems.pms.domain.dto.security.LoginUsrInfoDto;
import com.tysystems.pms.domain.dto.security.QLoginUsrInfoDto;
import com.tysystems.pms.domain.entity.pjt.QPjtStfOview;
import com.tysystems.pms.web.form.security.LoginRequestForm;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static com.tysystems.pms.domain.entity.pjt.QPjt.pjt;
import static com.tysystems.pms.domain.entity.pjt.QPjtStfOview.*;
import static com.tysystems.pms.domain.entity.usr.QUsr.usr;

@RequiredArgsConstructor
public class UsrLoginCustomRepositoryImpl implements UsrLoginCustomRepository {
    private final JPAQueryFactory queryFactory;

    private final JdbcTemplate template;
    @Override
    public List<LoginUsrInfoDto> loginUsrSearch(LoginRequestForm form) {
        List<LoginUsrInfoDto> fetch = queryFactory.select(new QLoginUsrInfoDto(
                        usr.usrId,
                        usr.usrPwd,
                        pjt.id,
                        usr.usrTypCd
                ))
                .from(usr)
                .join(pjtStfOview).on(usr.usrId.eq(pjtStfOview.pjtStfOviewPK.usrId))
                .join(pjtStfOview).on(pjt.id.eq(pjtStfOview.pjtStfOviewPK.pjtId))
                .where(
                        usr.usrId.eq(form.getId())
                                .and(pjt.id.eq(form.getPjtId())))
                .fetch();

        return fetch;
    }

    @Override
    public List<LoginUsrAuthDto> selectLoginUsrAuth(LoginRequestForm form) {
        String query = "SELECT USR_ID, AUTH_ID AS AUTHORITY " +
                "FROM (SELECT * FROM F_AUTH_USR) M " +
                "WHERE PJT_ID = ? AND USR_ID = ? " +
                "UNION " +
                "SELECT '', 'ROLE_NONE' " +
                "FROM DUAL";
        List<LoginUsrAuthDto> result = template.query(query, new Object[]{form.getPjtId(), form.getId()},
                (rs, rowNum) ->
                        new LoginUsrAuthDto(rs.getString("USR_ID"), rs.getString("AUTHORITY")));
        return result;
    }
}
