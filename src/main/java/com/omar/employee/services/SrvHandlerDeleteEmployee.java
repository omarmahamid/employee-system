package com.omar.employee.services;


import com.omar.employee.repository.DataRepository;
import com.omar.employee.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service(SrvHandlerVCB.DELETE_EMPLOYEE)
public class SrvHandlerDeleteEmployee implements ISrvHandler{

    private final Logger LOGGER = LoggerFactory.getLogger(SrvHandlerDeleteEmployee.class);

    private final DataRepository repository;

    @Autowired
    public SrvHandlerDeleteEmployee(DataRepository repository){
        this.repository = repository;
    }

    public void deleteEmployee(String employeeId){

        try {
            repository.deleteById(employeeId);
        }catch (EmptyResultDataAccessException e){
            LOGGER.warn("id not found, employee not deleted");
        }
    }
}
