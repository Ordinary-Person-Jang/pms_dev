package com.tysystems.pms.domain.filter.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tysystems.pms.domain.constant.common.Constants;
import com.tysystems.pms.domain.dto.security.LoginUsrAuthDto;
import com.tysystems.pms.domain.entity.security.PrincipalDetails;
import com.tysystems.pms.domain.entity.security.RefreshToken;
import com.tysystems.pms.domain.entity.usr.Role;
import com.tysystems.pms.domain.repository.security.UsrRedisRepository;
import com.tysystems.pms.domain.service.security.LoginService;
import com.tysystems.pms.web.form.security.LoginRequestForm;
import com.tysystems.pms.web.handler.exHandler.ErrorCode;
import com.tysystems.pms.web.handler.exHandler.ErrorResult;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private UserDetailsService detailsService;
    private UsrRedisRepository redisRepository;
    private LoginService loginService;


    public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
                                  UserDetailsService detailsService, UsrRedisRepository redisRepository, LoginService loginService) {
        super(authenticationManager);
        this.detailsService = detailsService;


        this.redisRepository = redisRepository;
        this.loginService = loginService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String jwtHeader = request.getHeader(JwtProperties.HEADER_STRING);

        if (jwtHeader == null || !jwtHeader.startsWith(JwtProperties.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        String token = request.getHeader(JwtProperties.HEADER_STRING).replace(JwtProperties.TOKEN_PREFIX, "");
        String pjtId = null;
        String loginId = null;

        try {
            pjtId = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET))
                    .build().verify(token).getClaim("pjtID").asString();
            loginId = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET))
                    .build().verify(token).getClaim("loginID").asString();
        } catch (Exception e) {
            exceptionHandler(response, ErrorCode.Expried_Token);
        }

        boolean byID = redisRepository.existsById(token);

        if(byID) {
            redisRepository.save(new RefreshToken(token, loginId));
            String loginInfoStr = loginId + "::" + pjtId;

            PrincipalDetails principalDetails = (PrincipalDetails) detailsService.loadUserByUsername(loginInfoStr);
            principalDetails.getUsr().setRole(loginService.setLoginUsrAuth(new LoginRequestForm(loginId, pjtId)));

            String suYn="N";
            List<LoginUsrAuthDto> roles = principalDetails.getUsr().getRole();
            for(LoginUsrAuthDto role : roles){
                log.debug("롤  : " + role);
                //관리자 롤이 있는 경우
                if(Constants.ADMIN_AUTH.equals(role.getAuthority())
                        || Role.PM.name().equals(role.getAuthority())
                        || Role.QAM.name().equals(role.getAuthority())){
                    suYn="Y";
                }
            }
            principalDetails.getUsr().setSuYn(suYn);

            UsernamePasswordAuthenticationToken logintoken =
                    new UsernamePasswordAuthenticationToken(principalDetails, "" ,principalDetails.getAuthorities());

//            PrincipalDetails principalDetails = (PrincipalDetails) userDetails;
//            request.setAttribute("principal", principalDetails);

            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(logintoken);
            SecurityContextHolder.setContext(context);

            log.info("SecurityContextHolder.getContext() [{}]", SecurityContextHolder.getContext());
            chain.doFilter(request, response);
        }else {
            exceptionHandler(response, ErrorCode.No_Exist_Token);
        }
        log.info("[{}] [{}] [{}]",token, loginId, byID);

    }


    public void exceptionHandler(HttpServletResponse response, ErrorCode error) {
        response.setStatus(error.getCode());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            ObjectMapper mapper = new ObjectMapper();
            ErrorResult errorResult = new ErrorResult(String.valueOf(error.getCode()), error.getMsg());
            String json = mapper.writeValueAsString(errorResult);
            response.getWriter().write(json);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
