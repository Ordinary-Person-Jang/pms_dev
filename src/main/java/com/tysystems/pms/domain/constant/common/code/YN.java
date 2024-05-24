/**
 * 파일명 : YN.java
 * 설  명 : 
 * 작성자 : 류양선
 * 작성일 : 2017. 4. 25.
 * 
 * 수정 이력 : 
 * 
 * 기타 사항 : 
 *
 * COPYRIGHTS (C) TONGYANG NETWORKS. ALL RIGHTS RESERVED.
 */

package com.tysystems.pms.domain.constant.common.code;

/**
 *
 * @author 류양선
 *
 */
public enum YN {
	// 사용
	Y("사용"),
	// 미사용
	N("미사용");
	
	private String krNm;
	
	private YN(String krNm) {
		this.krNm = krNm;
	}
	
    /**
     * 
     * <pre>
     * 한글명을 얻음
     * </pre>
     *
     * @return
     */
	public String getKrNm() {
		return this.krNm;
	}

}
