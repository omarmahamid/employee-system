package com.omar.employee.model;


import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDTO{
    private String employeeId;
    private String firstName;
    private String lastName;
    private String employeeMail;
}
