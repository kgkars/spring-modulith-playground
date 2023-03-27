package kgkars.spring.modulith.playground.user.internal.repository;

import kgkars.spring.modulith.playground.user.internal.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
