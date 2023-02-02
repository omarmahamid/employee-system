package com.omar.employee.repository;


import com.omar.employee.cache.CacheEmployeeService;
import com.omar.employee.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataRepository{

    private final Logger LOGGER = LoggerFactory.getLogger(DataRepository.class);

    private final EmployeeRepository jpaRepository;
    private final CacheEmployeeService cacheRepository;

    @Autowired
    public DataRepository(EmployeeRepository jpaRepository,
                          CacheEmployeeService cacheRepository){

        this.jpaRepository = jpaRepository;
        this.cacheRepository = cacheRepository;
    }

    /**
     * @param: employee
     * write-through cache
     * */
    public void save(Employee employee){
        cacheRepository.save(employee);
        jpaRepository.save(employee);
    }


    /**
     * @param: id
     * read-through cache
     * */
    public Employee getById(String employeeId){
        Employee employee = cacheRepository.getById(employeeId);
        if (employee == null){
            LOGGER.info("cache miss - going to retrieve employeeId {} from DB", employeeId);
            employee = jpaRepository.findById(employeeId).orElse(null);
            if (employee != null) {
                LOGGER.info("adding employee {} to cache", employee);
                cacheRepository.save(employee);
            }
        }
        return employee;
    }


    public void deleteById(String employeeId){
        cacheRepository.deleteEmployee(employeeId);
        jpaRepository.deleteById(employeeId);
    }
}
