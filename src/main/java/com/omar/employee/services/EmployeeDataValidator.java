package com.omar.employee.services;


import com.omar.employee.request.EmployeeRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmployeeDataValidator{


    private final Pattern emailRegex;

    public EmployeeDataValidator() {
        emailRegex = Pattern.compile("^(.+)@(\\S+)$");
    }


    public List<String> validateEmployeeData(EmployeeRequest employeeRequest) {
        List<String> missingData = new ArrayList<>();

        if (isNullOrEmpty(employeeRequest.getEmployeeId())) {
            missingData.add("employeeId");
        }

        if (isNullOrEmpty(employeeRequest.getFirstName())) {
            missingData.add("firstName");
        }

        if (isNullOrEmpty(employeeRequest.getLastName())) {
            missingData.add("lastName");
        }

        if (isNullOrEmpty(employeeRequest.getEmployeeMail()) || !isValidEmail(employeeRequest.getEmployeeMail())) {
            missingData.add("email");
        }

        return missingData;
    }

    private boolean isNullOrEmpty(String input) {
        return input == null || input.isEmpty();
    }

    private boolean isValidEmail(String email) {
        Matcher emailMatcher = emailRegex.matcher(email);
        return emailMatcher.matches();
    }

}
