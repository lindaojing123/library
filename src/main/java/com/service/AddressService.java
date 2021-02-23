package com.service;

import com.entity.Address;

import java.net.Inet4Address;
import java.util.*;

public interface AddressService {

    void saveAddress(Address address);
    List<Address> getAddressByUser(Integer userId);
    Address getAddressById(int id);
}
