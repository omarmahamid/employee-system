package com.omar.employee.request;


public class EmployeeRequest{

    private String employeeId;
    private String firstName;
    private String lastName;
    private String employeeMail;


    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmployeeMail() {
        return employeeMail;
    }

    public void setEmployeeMail(String employeeMail) {
        this.employeeMail = employeeMail;
    }


    @Override
    public String toString() {
        return "{" +
                "employeeId='" + employeeId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", employeeMail='" + employeeMail + '\'' +
                '}';
    }
}
