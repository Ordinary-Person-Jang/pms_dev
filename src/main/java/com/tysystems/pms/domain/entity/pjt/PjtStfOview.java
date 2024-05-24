package com.tysystems.pms.domain.entity.pjt;

import com.tysystems.pms.domain.entity.usr.Usr;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class PjtStfOview {

    @Id
    @EmbeddedId
    private PjtStfOviewPK pjtStfOviewPK;
    private String putPlnStrtYmdString;
    private String putPlnEndYmd;
    private String putPerfStrtYmd;
    private String putPerfEndYmd;
    private String putStaCd;
    private String putEndReasnCtnt;
    private String regUsrId;
    private String regYmd;
    private String mdfyUsrId;
    private String mdfyYmd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USR_ID", referencedColumnName = "USR_ID")
    @MapsId("usrId")
    private Usr usr;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PJT_ID", referencedColumnName = "PJT_ID")
    @MapsId("pjtId")
    private Pjt pjt;
}
