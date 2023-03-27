package kgkars.spring.modulith.playground.user.internal.service;

import jakarta.transaction.Transactional;
import kgkars.spring.modulith.playground.common.dto.AddressDTO;
import kgkars.spring.modulith.playground.user.internal.entity.Address;
import kgkars.spring.modulith.playground.common.AddressType;
import kgkars.spring.modulith.playground.common.dto.NewUserDTO;
import kgkars.spring.modulith.playground.common.Role;
import kgkars.spring.modulith.playground.user.internal.entity.User;
import kgkars.spring.modulith.playground.user.UserService;
import kgkars.spring.modulith.playground.user.internal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    @Override
    @Transactional
    public User create(NewUserDTO newUser) {
        User user = new User();

        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setEmail(newUser.getEmail());
        user.setPassword(_passwordEncoder.encode(newUser.getPassword()));
        user.setRole(Role.valueOf(newUser.getRole()));

        _userRepository.save(user);

        if (newUser.getAddresses() != null && newUser.getAddresses().length > 0) {

            for (AddressDTO dtoAddress : newUser.getAddresses()) {

                log.info(dtoAddress.toString());

                Address address = new Address();
                List<AddressType> addressTypes = new ArrayList<>();

                for (String dtoAddressType : dtoAddress.getAddressTypes()) {
                    addressTypes.add(AddressType.valueOf(dtoAddressType));
                }

                address.setAddressLineOne(dtoAddress.getAddressLineOne());
                address.setAddressLineTwo(dtoAddress.getAddressLineTwo());
                address.setCity(dtoAddress.getCity());
                address.setState(dtoAddress.getState());
                address.setCountry(dtoAddress.getCountry());
                address.setPostalCode(dtoAddress.getPostalCode());
                address.setAddressTypes(addressTypes);
                address.setUser(user);

                _addressService.saveNewAddress(address);
            }

        }

        return user;
    }

    @Override
    public User findUserById(UUID id) {
        return _userRepository.findById(id)
                .orElseThrow();
    }

    private final AddressService _addressService;

    private final ApplicationEventPublisher _publisher;

    private final PasswordEncoder _passwordEncoder;

    private final UserRepository _userRepository;

}
