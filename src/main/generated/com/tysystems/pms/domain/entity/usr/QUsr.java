package com.tysystems.pms.domain.entity.usr;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUsr is a Querydsl query type for Usr
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUsr extends EntityPathBase<Usr> {

    private static final long serialVersionUID = -228366715L;

    public static final QUsr usr = new QUsr("usr");

    public final com.tysystems.pms.domain.entity.common.QBaseEntity _super = new com.tysystems.pms.domain.entity.common.QBaseEntity(this);

    public final StringPath blngtCmpnyNm = createString("blngtCmpnyNm");

    public final StringPath cellPhoneNo = createString("cellPhoneNo");

    public final NumberPath<Integer> crerYCnt = createNumber("crerYCnt", Integer.class);

    public final StringPath dutyCd = createString("dutyCd");

    public final StringPath dutyNm = createString("dutyNm");

    public final StringPath emailAddr = createString("emailAddr");

    //inherited
    public final StringPath mdfyUsrId = _super.mdfyUsrId;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> mdfyYmd = _super.mdfyYmd;

    public final StringPath picAtchFileId = createString("picAtchFileId");

    public final StringPath pstnCd = createString("pstnCd");

    public final StringPath pstnNm = createString("pstnNm");

    //inherited
    public final StringPath regUsrId = _super.regUsrId;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regYmd = _super.regYmd;

    public final ListPath<com.tysystems.pms.domain.entity.pjt.PjtStfOview, com.tysystems.pms.domain.entity.pjt.QPjtStfOview> stfOviews = this.<com.tysystems.pms.domain.entity.pjt.PjtStfOview, com.tysystems.pms.domain.entity.pjt.QPjtStfOview>createList("stfOviews", com.tysystems.pms.domain.entity.pjt.PjtStfOview.class, com.tysystems.pms.domain.entity.pjt.QPjtStfOview.class, PathInits.DIRECT2);

    public final StringPath techGrdCd = createString("techGrdCd");

    public final StringPath usrDeptCd = createString("usrDeptCd");

    public final StringPath usrDeptNm = createString("usrDeptNm");

    public final StringPath usrDivisionCd = createString("usrDivisionCd");

    public final StringPath usrDivisionNm = createString("usrDivisionNm");

    public final StringPath usrHeadQuartersCd = createString("usrHeadQuartersCd");

    public final StringPath usrHeadquartersNm = createString("usrHeadquartersNm");

    public final StringPath usrId = createString("usrId");

    public final StringPath usrNm = createString("usrNm");

    public final StringPath usrPwd = createString("usrPwd");

    public final StringPath usrTeamCd = createString("usrTeamCd");

    public final StringPath usrTeamNm = createString("usrTeamNm");

    public final EnumPath<Authority> usrTypCd = createEnum("usrTypCd", Authority.class);

    public QUsr(String variable) {
        super(Usr.class, forVariable(variable));
    }

    public QUsr(Path<? extends Usr> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUsr(PathMetadata metadata) {
        super(Usr.class, metadata);
    }

}

