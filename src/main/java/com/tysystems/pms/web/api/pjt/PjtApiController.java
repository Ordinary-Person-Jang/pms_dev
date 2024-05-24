package com.tysystems.pms.web.api.pjt;

import com.tysystems.pms.domain.dto.pjt.PjtInfoDto;
import com.tysystems.pms.domain.service.pjt.PjtInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pjt/pjtInfo")
public class PjtApiController {

    private final PjtInfoService pjtInfoService;
    @GetMapping("/getPjtList")
    public List<PjtInfoDto> getPjtList() {
        log.info("Call PjtApiController [ getPjtList ]");
        return pjtInfoService.selectPjtList();
    }
}
