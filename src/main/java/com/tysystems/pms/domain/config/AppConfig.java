package com.tysystems.pms.domain.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tysystems.pms.domain.utils.CommUtil;
import com.tysystems.pms.domain.utils.QuerydslUtil;
import jakarta.persistence.EntityManager;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AppConfig {

    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager em) {
        return new JPAQueryFactory(em);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommUtil uitls(){return new CommUtil();}

    @Bean
    public QuerydslUtil querydslUtil() { return new QuerydslUtil(); }

}
