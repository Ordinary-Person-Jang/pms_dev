package com.tysystems.pms.domain.repository.security;

import com.tysystems.pms.domain.entity.security.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface UsrRedisRepository extends CrudRepository<RefreshToken, String> {
}
