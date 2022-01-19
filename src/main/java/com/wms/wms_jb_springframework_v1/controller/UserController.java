package com.wms.wms_jb_springframework_v1.controller;

import com.wms.wms_jb_springframework_v1.model.Admin;
import com.wms.wms_jb_springframework_v1.model.Employee;
import com.wms.wms_jb_springframework_v1.model.Item;
import com.wms.wms_jb_springframework_v1.model.LoginDTO;
import com.wms.wms_jb_springframework_v1.service.UserService;
import com.wms.wms_jb_springframework_v1.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/getAllEmployees")
    public List<Employee> getAllEmployees() {
        return userService.getAllEmployees();
    }

    @GetMapping(path = "/getAllAdmins")
    public List<Admin> getAllAdmins() {
        return userService.getAllAdmins();
    }

    @GetMapping(path = "/employee/login")
    public boolean loginEmployee(@RequestBody LoginDTO credentials) {
        return userService.isEmployeeValid(credentials.getUsername(), credentials.getPassword());
    }

    @GetMapping(path = "/admin/login")
    public boolean loginAdmin(@RequestBody LoginDTO credentials) {
        return userService.isAdminValid(credentials.getUsername(), credentials.getPassword());
    }

}
