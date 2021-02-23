package com.service;

import com.dao.AddressDao;
import com.dao.AdminDao;
import com.entity.Address;
import com.entity.Admin;
import com.util.MybatisUtil;

import java.util.List;

public class AddressServiceImpl implements AddressService{


    @Override
    public void saveAddress(Address address) {
        try{
            AddressDao mapper = MybatisUtil.getMapper(AddressDao.class);
            mapper.saveAddress(address);
            MybatisUtil.commit();
        }catch (Exception e){
            e.printStackTrace();
            MybatisUtil.rollback();
            throw new RuntimeException(e.getMessage());
        }finally {
            MybatisUtil.close();
        }
    }

    @Override
    public List<Address> getAddressByUser(Integer userId) {
        try{
            AddressDao addressDao = MybatisUtil.getMapper(AddressDao.class);
            List<Address> list = addressDao.selectAllByUser(userId);
            return list;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally {
            MybatisUtil.close();
        }
    }

    @Override
    public Address getAddressById(int id) {
        try{
            AddressDao addressDao = MybatisUtil.getMapper(AddressDao.class);
            Address address = addressDao.getOneById(id);
            return address;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally {
            MybatisUtil.close();
        }
    }
}
