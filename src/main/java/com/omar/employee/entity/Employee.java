package com.omar.employee.entity;


import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EMPLOYEE")
public class Employee{

    @Id
    @Column(name = "EMPLOYEE_ID")
    private String employeeId;

    @Column(name = "EMPLOYEE_FIRST_NAME")
    private String firstName;

    @Column(name = "EMPLOYEE_LAST_NAME")
    private String lastName;

    @Column(name = "EMPLOYEE_MAIL")
    private String employeeMail;
}
