package kgkars.spring.modulith.playground.audit.internal.service;

import kgkars.spring.modulith.playground.audit.internal.entity.LoginAudit;
import kgkars.spring.modulith.playground.audit.internal.repository.LoginAuditRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class LoginAuditService {
    public void save(LoginAudit loginAuditRecord) {
        _loginAuditRepository.save(loginAuditRecord);
    }

    private final LoginAuditRepository _loginAuditRepository;
}