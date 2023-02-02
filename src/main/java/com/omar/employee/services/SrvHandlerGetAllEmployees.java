package com.omar.employee.services;


import com.omar.employee.entity.Employee;
import com.omar.employee.model.EmployeeDTO;
import com.omar.employee.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service(SrvHandlerVCB.GET_EMPLOYEES)
public class SrvHandlerGetAllEmployees implements ISrvHandler{


    private final Logger LOGGER = LoggerFactory.getLogger(SrvHandlerGetAllEmployees.class);

    private final EmployeeRepository repository;

    public SrvHandlerGetAllEmployees(EmployeeRepository repository){
        this.repository = repository;
    }


    public List<EmployeeDTO> getAllEmployees(){

        LOGGER.info("fetching all employees from DB");

        List<Employee> employees = repository.findAll();

        return employees.stream().map(this::getEmployeeDTO).collect(Collectors.toList());

    }

    private EmployeeDTO getEmployeeDTO(Employee employee){
        return EmployeeDTO.builder()
                .employeeId(employee.getEmployeeId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .employeeMail(employee.getEmployeeMail())
                .build();
    }

}
