package kgkars.spring.modulith.playground.user.internal.service;

import jakarta.transaction.Transactional;
import kgkars.spring.modulith.playground.auth.RegisterNewUserEvent;
import kgkars.spring.modulith.playground.common.dto.AddressDTO;
import kgkars.spring.modulith.playground.common.dto.UserRegistrationRequest;
import kgkars.spring.modulith.playground.user.internal.entity.Address;
import kgkars.spring.modulith.playground.common.AddressType;
import kgkars.spring.modulith.playground.common.Role;
import kgkars.spring.modulith.playground.user.User;
import kgkars.spring.modulith.playground.user.UserService;
import kgkars.spring.modulith.playground.user.internal.exception.UserNotFoundException;
import kgkars.spring.modulith.playground.user.internal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    @EventListener
    public void registerNewUser(RegisterNewUserEvent event) {
        User user = create(event.getRequest());
    }

    @Override
    @Transactional
    public User create(UserRegistrationRequest request) {
        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(_passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.valueOf(request.getRole()));
        user.setEnabled(true);

        _userRepository.save(user);


        if (request.getAddresses() != null && request.getAddresses().length > 0) {

            for (AddressDTO dtoAddress : request.getAddresses()) {

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
    public Optional<User> findUserByEmail(String email) throws UserNotFoundException {
        return Optional.ofNullable(_userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("No user with found with email: " + email)));
    }

    @Override
    public User findUserById(UUID id) throws UserNotFoundException {
        return _userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No user with found with id: " + id));
    }

    private final AddressService _addressService;

    private final ApplicationEventPublisher _publisher;

    private final PasswordEncoder _passwordEncoder;

    private final UserRepository _userRepository;

}
