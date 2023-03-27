package kgkars.spring.modulith.playground.common.dto;

import kgkars.spring.modulith.playground.common.dto.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewUserDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private AddressDTO[] addresses;

}
