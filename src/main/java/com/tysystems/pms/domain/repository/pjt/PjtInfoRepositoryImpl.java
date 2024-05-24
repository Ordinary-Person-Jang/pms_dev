package com.tysystems.pms.domain.repository.pjt;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tysystems.pms.domain.dto.pjt.PjtInfoDto;
import com.tysystems.pms.domain.dto.pjt.QPjtInfoDto;
import com.tysystems.pms.domain.entity.pjt.QPjt;
import com.tysystems.pms.domain.utils.QuerydslUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.tysystems.pms.domain.entity.pjt.QPjt.*;
import static com.tysystems.pms.domain.entity.pjt.QPjt.pjt;

@Slf4j
@RequiredArgsConstructor
public class PjtInfoRepositoryImpl implements PjtInfoRepository {

    private final JPAQueryFactory queryFactory;
    private final QuerydslUtil querydslUtil;

    @Override
    public List<PjtInfoDto> selectPjtList() {
        List<PjtInfoDto> fetch = queryFactory
                .select(new QPjtInfoDto(
                        pjt.id,
                        pjt.pjtNm,
                        querydslUtil.dateFormat(pjt.strtYmd),
                        querydslUtil.dateFormat(pjt.endYmd),
                        pjt.custCmpnyNm
                )).from(pjt)
                .fetch();
        return fetch;
    }
}
