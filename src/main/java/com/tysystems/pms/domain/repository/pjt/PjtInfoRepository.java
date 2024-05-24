package com.tysystems.pms.domain.repository.pjt;

import com.tysystems.pms.domain.dto.pjt.PjtInfoDto;

import java.util.List;

public interface PjtInfoRepository {
    List<PjtInfoDto> selectPjtList();
}
