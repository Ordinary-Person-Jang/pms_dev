package com.tysystems.pms.domain.repository.pjt;

import com.tysystems.pms.domain.entity.pjt.Pjt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PjtRepository extends JpaRepository<Pjt, String>, PjtInfoRepository {
}
