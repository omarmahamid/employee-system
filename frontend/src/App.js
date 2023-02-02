import './App.css';
import {useState, useEffect} from "react";
import {getEmployees, addEmployee, deleteEmployee, updateEmployee, getEmployee} from "./api/handler";


function App() {


    const [data, setData] = useState([]);
    const [showTable, setShowTable] = useState(false);
    const [showInputAdd, setShowInputAdd] = useState(false);
    const [showInputDelete, setShowInputDelete] = useState(false);
    const [showInputUpdate, setShowInputUpdate] = useState(false);
    const [showInputGet, setShowInputGet] = useState(false);


    const fetchEmployeeData = () => {
        getEmployees().then(employees => {
            setData(employees);
            setShowTable(true);
        });
    };


    const handleEmployeeSubmit = async (event) => {
        event.preventDefault()
        const employeeId = event.target.employeeId.value;
        const employeeData = await getEmployee(employeeId);

        let employeeDataAsArray = [employeeData];

        console.log(employeeDataAsArray)

        setData(employeeDataAsArray);
        setShowTable(true);
    }


    const handleAddSubmit = (event) => {
        event.preventDefault();
        const employee = {
            employeeId: event.target.employeeId.value,
            firstName: event.target.firstName.value,
            lastName: event.target.lastName.value,
            employeeMail: event.target.employeeMail.value,
        };

        console.log(employee)
        addEmployee(employee);
    };


    const handleDeleteSubmit = (event) => {
        event.preventDefault();
        const employeeId = event.target.employeeId.value;
        deleteEmployee(employeeId);
    };


    const handleUpdateSubmit = (event) => {
        event.preventDefault();
        const employee = {
            employeeId: event.target.employeeId.value,
            firstName: event.target.firstName.value,
            lastName: event.target.lastName.value,
            employeeMail: event.target.employeeMail.value,
        };
        const employeeId = employee.employeeId;
        updateEmployee(employeeId, employee);
    };

    return (
        <div className="App">
            <p>Employee System</p>
            <form onSubmit={handleAddSubmit} className="form">
                {showInputAdd && (
                    <input
                        type="text"
                        name="employeeId"
                        placeholder="Employee Id"
                        className="form-input"
                    />
                )}
                {showInputAdd && (
                    <input
                        type="text"
                        name="firstName"
                        placeholder="First Name"
                        className="form-input"
                    />
                )}
                {showInputAdd && (
                    <input
                        type="text"
                        name="lastName"
                        placeholder="Last Name"
                        className="form-input"
                    />
                )}
                {showInputAdd && (
                    <input
                        type="email"
                        name="employeeMail"
                        placeholder="Email"
                        className="form-input"
                    />
                )}
                <button type="submit" className="form-button" onClick={() => setShowInputAdd(true)}>
                    Add Employee
                </button>
            </form>

            <form onSubmit={handleDeleteSubmit} className="form">
                {showInputDelete && (
                    <input
                        type="text"
                        name="employeeId"
                        placeholder="Employee Id"
                        className="form-input"
                    />
                )}
                <button type="submit" className="form-button" onClick={() => setShowInputDelete(true)}>
                    Delete Employee
                </button>
            </form>

            <form onSubmit={handleUpdateSubmit} className="form">
                {showInputUpdate && (
                    <input
                        type="text"
                        name="employeeId"
                        placeholder="Employee Id"
                        className="form-input"
                    />
                )}
                {showInputUpdate && (
                    <input
                        type="text"
                        name="firstName"
                        placeholder="First Name"
                        className="form-input"
                    />
                )}
                {showInputUpdate && (
                    <input
                        type="text"
                        name="lastName"
                        placeholder="Last Name"
                        className="form-input"
                    />
                )}
                {showInputUpdate && (
                    <input
                        type="email"
                        name="employeeMail"
                        placeholder="Email"
                        className="form-input"
                    />
                )}
                <button type="submit" className="form-button" onClick={() => setShowInputUpdate(true)}>
                    Update Employee
                </button>
            </form>


            <form onSubmit={handleEmployeeSubmit} className="form">
                {showInputGet && (
                    <input
                        type="text"
                        name="employeeId"
                        placeholder="Employee Id"
                        className="form-input"
                    />
                )}
                <button type="submit" className="form-button" onClick={() => setShowInputGet(true)}>
                    Get Employee
                </button>
            </form>

            <button onClick={fetchEmployeeData}>Get Employees</button>

            {data && (
                <table>
                    <thead>
                    <tr>
                        <th>id</th>
                        <th>firstName</th>
                        <th>lastName</th>
                        <th>mail</th>
                    </tr>
                    </thead>
                    <tbody>
                    {data.map(employee => (
                        <tr key={employee.employeeId}>
                            <td>{employee.employeeId}</td>
                            <td>{employee.firstName}</td>
                            <td>{employee.lastName}</td>
                            <td>{employee.employeeMail}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            )}
        </div>
    );
}

export default App;
