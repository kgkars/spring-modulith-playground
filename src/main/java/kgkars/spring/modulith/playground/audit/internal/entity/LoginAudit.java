package kgkars.spring.modulith.playground.audit.internal.entity;

import jakarta.persistence.*;
import kgkars.spring.modulith.playground.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_login_audit_history")
public class LoginAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Builder.Default private ZonedDateTime loginTime = ZonedDateTime.now(ZoneId.of("UTC"));
    private String username;
    private String role;
    private String requestUserAgent;
    private String requestIpAddress;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "userId",
            nullable = false
    )
    private User user;

}
