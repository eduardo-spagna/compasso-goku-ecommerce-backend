package com.compassouol.gokuecommerce.services;

import java.util.ArrayList;
import java.util.List;

import com.compassouol.gokuecommerce.dtos.request.address.CreateAddressRequestDTO;
import com.compassouol.gokuecommerce.dtos.response.PaginationResponseDTO;
import com.compassouol.gokuecommerce.dtos.response.address.ShowAddressResponseDTO;
import com.compassouol.gokuecommerce.enums.RoleEnum;
import com.compassouol.gokuecommerce.models.Address;
import com.compassouol.gokuecommerce.models.User;
import com.compassouol.gokuecommerce.models.UserRole;
import com.compassouol.gokuecommerce.repositories.AddressRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public PaginationResponseDTO findAllWithSearchAndPagination(Integer page, Integer perPage, String search,
            User user) {
        Pageable pagination = PageRequest.of(page - 1, perPage);
        Page<Address> paginationResult = null;

        boolean isAdmin = false;
        for (UserRole userRole : user.getRoles()) {
            if (userRole.getRole().getRoleId() == RoleEnum.ROLE_ADMIN.getRoleId()) {
                isAdmin = true;
            }
        }

        if (search == null) {
            search = "";
        }

        if (isAdmin == true) {
            paginationResult = addressRepository.findAll(search, pagination);
        } else {
            paginationResult = addressRepository.findAllByUserId(user.getUserId(), search, pagination);
        }

        List<Object> data;

        if (paginationResult.hasContent() == true) {
            List<ShowAddressResponseDTO> formatedResults = new ArrayList<>();

            for (Address address : paginationResult.getContent()) {
                ShowAddressResponseDTO addressPagination = new ShowAddressResponseDTO(address.getAddressId(),
                        address.getAddressZipCode(), address.getAddressStreet(), address.getAddressStreetNumber(),
                        address.getAddressDistrict(), address.getAddressComplement(), address.getAddressCity(),
                        address.getAddressState());

                formatedResults.add(addressPagination);
            }

            data = new ArrayList<>(formatedResults);
        } else {
            data = new ArrayList<>();
        }

        double lastPage = (double) paginationResult.getTotalElements() / perPage;
        PaginationResponseDTO responsePagination = new PaginationResponseDTO(paginationResult.getTotalElements(),
                perPage, paginationResult.getNumber() + 1,
                (paginationResult.getTotalElements() / perPage == 0 ? 1 : (long) Math.ceil(lastPage)), data);

        return responsePagination;
    }
}
