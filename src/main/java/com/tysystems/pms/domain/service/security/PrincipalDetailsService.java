package com.tysystems.pms.domain.service.security;

import com.tysystems.pms.domain.dto.security.LoginUsrInfoDto;
import com.tysystems.pms.domain.entity.security.PrincipalDetails;
import com.tysystems.pms.domain.repository.usr.UsrRepository;
import com.tysystems.pms.web.form.security.LoginRequestForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalDetailsService implements UserDetailsService {

    private final UsrRepository repository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
//        Optional<Usr> usr = repository.findUsrById(loginId).stream().findFirst();
        String[] split = loginId.split("::");
        LoginRequestForm form = new LoginRequestForm(split[0], split[1]);
        Optional<LoginUsrInfoDto> usrInfo = repository.loginUsrSearch(form).stream().findFirst();

        if (usrInfo.isEmpty()) {
            throw new InternalAuthenticationServiceException("존재하지 않는 회원입니다.");
        }
        LoginUsrInfoDto dto = usrInfo.get();
        log.info("LoginUsrInfoDto [{}][{}]", dto.getUsrId(), dto.getPjtId());

        return new PrincipalDetails(usrInfo.get());
    }
}
