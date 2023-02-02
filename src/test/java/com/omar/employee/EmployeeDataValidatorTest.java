package com.omar.employee;

import com.omar.employee.request.EmployeeRequest;
import com.omar.employee.services.EmployeeDataValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;



class EmployeeDataValidatorTest{

    static EmployeeDataValidator employeeDataValidator;

    @BeforeAll
    static void setUp(){
        employeeDataValidator = new EmployeeDataValidator();
    }

    @Test
    void noIdData(){

        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setEmployeeId(null);
        employeeRequest.setFirstName("Omar");
        employeeRequest.setLastName("Mahamid");
        employeeRequest.setEmployeeMail("omar.taiseerr@gmail.com");

        Assertions.assertNotEquals(0, employeeDataValidator.validateEmployeeData(employeeRequest).size());
    }


    @Test
    void emptyFirstName(){

        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setEmployeeId("123");
        employeeRequest.setFirstName("");
        employeeRequest.setLastName("Mahamid");
        employeeRequest.setEmployeeMail("omar.taiseerr@gmail.com");

        Assertions.assertNotEquals(0, employeeDataValidator.validateEmployeeData(employeeRequest).size());
    }


    @Test
    void nonValidMail(){

        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setEmployeeId("123");
        employeeRequest.setFirstName("Omar");
        employeeRequest.setLastName("Mahamid");
        employeeRequest.setEmployeeMail("omar.taiseerrgmail.com");

        Assertions.assertNotEquals(0, employeeDataValidator.validateEmployeeData(employeeRequest).size());
    }


    @Test
    void allValid(){

        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setEmployeeId("123");
        employeeRequest.setFirstName("Omar");
        employeeRequest.setLastName("Mahamid");
        employeeRequest.setEmployeeMail("omar.mahamid@gmail.com");

        Assertions.assertEquals(0, employeeDataValidator.validateEmployeeData(employeeRequest).size());
    }

}
