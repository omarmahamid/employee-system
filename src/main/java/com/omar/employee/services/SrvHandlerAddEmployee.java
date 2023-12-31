package com.omar.employee.services;


import com.omar.employee.entity.Employee;
import com.omar.employee.exceptions.EmployeeDataNotFoundException;
import com.omar.employee.repository.DataRepository;
import com.omar.employee.request.EmployeeRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(SrvHandlerVCB.ADD_EMPLOYEE)
public class SrvHandlerAddEmployee implements ISrvHandler{

    private final Logger LOGGER = LoggerFactory.getLogger(SrvHandlerAddEmployee.class);

    private final DataRepository repository;
    private final EmployeeDataValidator mailValidatorComponent;

    @Autowired
    public SrvHandlerAddEmployee(DataRepository repository,
                                 EmployeeDataValidator mailValidatorComponent){
        this.repository = repository;
        this.mailValidatorComponent = mailValidatorComponent;
    }


    public String addEmployee(EmployeeRequest employeeRequest){

        LOGGER.info("adding employee {}", employeeRequest.toString());

        List<String> missingData = mailValidatorComponent.validateEmployeeData(employeeRequest);

        if (!missingData.isEmpty()){
            String errorMessage = String.format("Employee data %s are missing, or the mail %s not valid", missingData, employeeRequest.getEmployeeMail());
            LOGGER.error(errorMessage);
            throw new EmployeeDataNotFoundException(errorMessage);
        }

        Employee employee = Employee.builder()
                .employeeId(employeeRequest.getEmployeeId())
                .firstName(employeeRequest.getFirstName())
                .lastName(employeeRequest.getLastName())
                .employeeMail(employeeRequest.getEmployeeMail())
                .build();

        repository.save(employee);

        LOGGER.info("employee {} added successfully", employeeRequest);

        return employee.getEmployeeId();
    }
}
