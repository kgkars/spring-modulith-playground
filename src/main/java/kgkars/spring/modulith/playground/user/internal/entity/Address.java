package kgkars.spring.modulith.playground.user.internal.entity;

import jakarta.persistence.*;
import kgkars.spring.modulith.playground.common.AddressType;
import kgkars.spring.modulith.playground.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Address Entity for tbl_user_address table.
 *
 * @author Kris Karstedt
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_user_address")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressId;
    private String addressLineOne;
    private String addressLineTwo;
    private String city;
    private String state;
    private String country;
    private String postalCode;

    @Enumerated(EnumType.STRING)
    private List<AddressType> addressTypes;

    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "userId",
            nullable = false
    )
    private User user;

    public boolean isPrimary() {
        return addressTypes.size() > 0 && addressTypes.contains("Primary");
    }

    public boolean isMailing() {
        return addressTypes.size() > 0 && addressTypes.contains("Mailing");
    }


}
