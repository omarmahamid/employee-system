package com.omar.employee.services;


import com.omar.employee.request.EmployeeRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service(SrvHandlerVCB.UPDATE_EMPLOYEE)
public class SrvHandlerUpdateEmployee implements ISrvHandler{

    private final Logger LOGGER = LoggerFactory.getLogger(SrvHandlerUpdateEmployee.class);

    private final SrvHandlerAddEmployee srvHandlerAddEmployee;

    @Autowired
    public SrvHandlerUpdateEmployee(SrvHandlerAddEmployee srvHandlerAddEmployee){
        this.srvHandlerAddEmployee = srvHandlerAddEmployee;
    }

    public void updateEmployee(String id, EmployeeRequest request){

        LOGGER.info("going to update employee");

        if (!request.getEmployeeId().equals(id)){
            LOGGER.error("we can't update employee id, should be the same id in PUT request");
            return;
        }
        srvHandlerAddEmployee.addEmployee(request);
    }
}
