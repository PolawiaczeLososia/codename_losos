package com.erpsystem.entity.address;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressService {

  @Autowired
  private AddressRepository addressRepository;

  public Address save(Address address) {

    addressRepository.save(address);
    return address;
  }

  public List<Address> findAll() {
    return addressRepository.findAll();
  }

  public Address findById(String id) {
    return addressRepository.findById(id).orElse(null);
  }

  public Address overwrite(Address address) {
    Address overwrittenAddress = addressRepository.findById(address.getId()).orElse(null);
    if (overwrittenAddress != null) {
      addressRepository.save(address);
    }
    return overwrittenAddress;
  }

  public void deleteAll() {
    addressRepository.deleteAll();
  }

  public void delete(Address address) {
    addressRepository.delete(address);
  }

  public Address deleteById(String id) {
    Address address = addressRepository.findById(id).orElse(null);
    addressRepository.deleteById(id);
    return address;
  }
}
