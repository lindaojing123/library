package com.dao;

import com.entity.Address;
import java.util.*;

public interface AddressDao {

    public void saveAddress(Address address);
    public List<Address> selectAllByUser(Integer userId);
    Address getOneById(int id);
}
