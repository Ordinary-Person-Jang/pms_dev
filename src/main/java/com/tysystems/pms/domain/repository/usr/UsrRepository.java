package com.tysystems.pms.domain.repository.usr;

import com.tysystems.pms.domain.entity.usr.Usr;
import com.tysystems.pms.domain.repository.security.UsrLoginCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsrRepository extends JpaRepository<Usr, String>, UsrLoginCustomRepository {

    List<Usr> findUsrByUsrId(String id);
}
