package com.kevinthelago.pickle_ball.repo;

import com.kevinthelago.pickle_ball.dao.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}
