package com.compassouol.gokuecommerce.repositories;

import com.compassouol.gokuecommerce.models.Address;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query(value = "SELECT * " + "FROM addresses "
            + "WHERE user_id = :userId AND addresses.address_zip_code ILIKE %:search% "
            + "ORDER BY address_id ASC", nativeQuery = true)
    Page<Address> findAllByUserId(long userId, String search, Pageable pageable);

    @Query(value = "SELECT * " + "FROM addresses " + "WHERE addresses.address_zip_code ILIKE %:search% "
            + "ORDER BY address_id ASC", nativeQuery = true)
    Page<Address> findAll(String search, Pageable pageable);
}