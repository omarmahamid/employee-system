// api.js
import axios from 'axios';

export const getEmployees = async () => {
    const response = await axios.get('http://localhost:8080/employee');
    return response.data;
};

export const getEmployee = async (employeeId) => {
    const response = await axios.get(`http://localhost:8080/employee/${employeeId}`)
    return response.data
}

export const addEmployee = async (employee) => {
    const response = await axios.post('http://localhost:8080/employee', employee);
    return response.data;
};


export const deleteEmployee = async (employeeId) => {
    const response = await axios.delete(`http://localhost:8080/employee/${employeeId}`);
    return response.data;
}

export const updateEmployee = async (employeeId, employee) => {
    const response = await axios.put(`http://localhost:8080/employee/${employeeId}`, employee);
    return response.data;
}
