package com.tysystems.pms.domain.entity.usr;

import com.tysystems.pms.domain.entity.pjt.PjtStfOview;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usr {
    @Id
    @Column(name = "USR_ID")
    private String usrId;
    private String usrPwd;
    private String usrNm;
    private String usrHeadQuartersCd;
    private String usrHeadquartersNm;
    private String usrDivisionCd;
    private String usrDivisionNm;
    private String usrDeptCd;
    private String usrDeptNm;
    private String usrTeamCd;
    private String usrTeamNm;

    @Column(name = "USR_TYP_CD", length = 3)
    @Enumerated(EnumType.STRING)
    private Authority usrTypCd;
    private String blngtCmpnyNm;
    private String pstnCd;
    private String pstnNm;
    private String dutyCd;
    private String dutyNm;
    private String cellPhoneNo;
    private String emailAddr;
    private String techGrdCd;
    private int crerYCnt;
    private String picAtchFileId;

    @OneToMany(mappedBy = "usr")
    private List<PjtStfOview> stfOviews = new ArrayList<>();

}
