package com.tysystems.pms.domain.service.pjt;

import com.tysystems.pms.domain.dto.pjt.PjtInfoDto;
import com.tysystems.pms.domain.repository.pjt.PjtInfoRepository;
import com.tysystems.pms.domain.repository.pjt.PjtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PjtInfoService {

    private final PjtRepository pjtRepository;
    public List<PjtInfoDto> selectPjtList() {
        return pjtRepository.selectPjtList();
    }

}
