package _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._2AdvancedMapping.org.softwareuniversityspringdata.service.impl;

import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._2AdvancedMapping.org.softwareuniversityspringdata.data.repositories.AddressRepository;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._2AdvancedMapping.org.softwareuniversityspringdata.data.entities.Address;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._2AdvancedMapping.org.softwareuniversityspringdata.service.AddressService;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public String createNewAddress(Address address) {
        this.addressRepository.saveAndFlush(address);
        return String.format("Create new address successfully");
    }
}
