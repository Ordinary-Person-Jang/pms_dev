package com.tysystems.pms.domain.entity.pjt;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Embeddable
@Getter
@RequiredArgsConstructor
public class PjtStfOviewPK {
    @Column(name = "PJT_ID")
    private String pjtId;

    @Column(name = "USR_ID")
    private String usrId;
}
