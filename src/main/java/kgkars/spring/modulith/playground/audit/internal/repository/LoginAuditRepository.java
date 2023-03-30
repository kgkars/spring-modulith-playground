package kgkars.spring.modulith.playground.audit.internal.repository;

import kgkars.spring.modulith.playground.audit.internal.entity.LoginAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LoginAuditRepository extends JpaRepository<LoginAudit, UUID> {
}
