package com.omar.employee.services;

import com.omar.employee.entity.Employee;
import com.omar.employee.model.EmployeeDTO;
import com.omar.employee.repository.DataRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service(SrvHandlerVCB.GET_EMPLOYEE)
public class SrvHandlerGetEmployee implements ISrvHandler{

    private final Logger LOGGER = LoggerFactory.getLogger(SrvHandlerGetEmployee.class);

    private final DataRepository repository;

    @Autowired
    public SrvHandlerGetEmployee(DataRepository repository){
        this.repository = repository;
    }


    public EmployeeDTO getEmployee(String id){

        LOGGER.info("Fetching employee id: {}", id);

        Employee employee = repository.getById(id);

        if (employee == null){
            LOGGER.warn("employeeId {} doesn't exist", id);
            return null;
        }

        return EmployeeDTO.builder()
                .employeeId(employee.getEmployeeId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .employeeMail(employee.getEmployeeMail())
                .build();
    }
}
