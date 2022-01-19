package com.wms.wms_jb_springframework_v1.service;

import com.wms.wms_jb_springframework_v1.model.Admin;
import com.wms.wms_jb_springframework_v1.model.Employee;
import com.wms.wms_jb_springframework_v1.model.Item;
import com.wms.wms_jb_springframework_v1.repository.UserRepository;
import com.wms.wms_jb_springframework_v1.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {

    public List<Employee> getAllEmployees() { return UserRepository.getAllEmployees(); }

    public List<Admin> getAllAdmins() { return UserRepository.getAllAdmins(); }

    public boolean isEmployeeValid(String userName, String password) {
        return UserRepository.isEmployeeValid(userName, password);
    }

    public boolean isAdminValid(String userName, String password) {
        return UserRepository.isAdminValid(userName, password);
    }

}
