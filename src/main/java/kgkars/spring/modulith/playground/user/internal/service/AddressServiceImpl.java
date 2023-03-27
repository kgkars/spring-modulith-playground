package kgkars.spring.modulith.playground.user.internal.service;

import kgkars.spring.modulith.playground.user.internal.entity.Address;
import kgkars.spring.modulith.playground.user.internal.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    @Override
    public void saveNewAddress(Address address) {
        _addressRepository.save(address);
    }

    private final AddressRepository _addressRepository;
}
