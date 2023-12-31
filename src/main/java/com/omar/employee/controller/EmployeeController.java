package com.omar.employee.controller;


import com.omar.employee.exceptions.EmployeeDataNotFoundException;
import com.omar.employee.model.EmployeeDTO;
import com.omar.employee.ratelimit.IRateLimitService;
import com.omar.employee.request.EmployeeRequest;
import com.omar.employee.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/employee")
public class EmployeeController{

    private final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);


    private final Map<String, ISrvHandler> srvHandlerMap;
    private final IRateLimitService rateLimitService;

    @Autowired
    public EmployeeController(Map<String, ISrvHandler> srvHandlerMap,
                              IRateLimitService rateLimitService){

        this.srvHandlerMap = srvHandlerMap;
        this.rateLimitService = rateLimitService;
    }

    @PostMapping
    public ResponseEntity<String> addEmployee(@RequestBody EmployeeRequest request) {

        LOGGER.info("post request, add employee {}", request.toString());

        if (rateLimitService.overLimit()){
            LOGGER.warn("RateLimit limited the requests, try in next minutes");
            return new ResponseEntity<>(HttpStatus.OK);
        }

        try {
            String employeeId = ((SrvHandlerAddEmployee) srvHandlerMap.get(SrvHandlerVCB.ADD_EMPLOYEE)).addEmployee(request);
            return new ResponseEntity<>(employeeId, HttpStatus.CREATED);
        }catch (EmployeeDataNotFoundException e) {
            String errorMessage = "Error: " + e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getEmployees() {

        LOGGER.info("get request, get all employees");

        List<EmployeeDTO> employeeDTOS = ((SrvHandlerGetAllEmployees) srvHandlerMap.get(SrvHandlerVCB.GET_EMPLOYEES)).getAllEmployees();
        return new ResponseEntity<>(employeeDTOS, HttpStatus.OK);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable("employeeId") String employeeId){

        LOGGER.info("get request, get employeeId {}", employeeId);

        EmployeeDTO employeeDTO = ((SrvHandlerGetEmployee) srvHandlerMap.get(SrvHandlerVCB.GET_EMPLOYEE)).getEmployee(employeeId);

        LOGGER.info("get request, get employeeId {} finished successfully", employeeDTO);
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<Void> updateEmployee(@PathVariable("employeeId") String employeeId,
                                               @RequestBody EmployeeRequest request){

        LOGGER.info("update employeeId {}", employeeId);

        ((SrvHandlerUpdateEmployee)srvHandlerMap.get(SrvHandlerVCB.UPDATE_EMPLOYEE)).updateEmployee(employeeId, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("employeeId") String employeeId){

        LOGGER.info("delete employee {}", employeeId);

        ((SrvHandlerDeleteEmployee)srvHandlerMap.get(SrvHandlerVCB.DELETE_EMPLOYEE)).deleteEmployee(employeeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
