package com.compassouol.gokuecommerce.services;

import com.compassouol.gokuecommerce.dtos.request.address.CreateAddressRequestDTO;
import com.compassouol.gokuecommerce.models.Address;
import com.compassouol.gokuecommerce.models.User;
import com.compassouol.gokuecommerce.repositories.AddressRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public Address create(CreateAddressRequestDTO createAddress, User user) {
        Address address = new Address();

        address.setAddressZipCode(createAddress.getAddressZipCode());
        address.setAddressStreet(createAddress.getAddressStreet());
        address.setAddressStreetNumber(createAddress.getAddressStreetNumber());
        address.setAddressDistrict(createAddress.getAddressDistrict());
        address.setAddressComplement(createAddress.getAddressComplement());
        address.setAddressCity(createAddress.getAddressCity());
        address.setAddressState(createAddress.getAddressState());
        address.setUser(user);

        return addressRepository.save(address);
    }
}
