package com.omar.employee.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.omar.employee.entity.Employee;
import org.springframework.stereotype.Service;

@Service
public class CacheEmployeeService{

    LoadingCache<String, Employee> cache;

    public CacheEmployeeService(){
        cache = Caffeine.newBuilder()
                .maximumSize(100)
                .build(k -> new Employee());
    }


    /**
     * @param : Employee
     * save Employee in the cache
     * */
    public void save(Employee employee){
        cache.put(employee.getEmployeeId(), employee);
    }


    /**
     * @param: id
     * @return: Employee from the cache
     * */
    public Employee getById(String id){
        return cache.getIfPresent(id);
    }


    /**
     * @param: id
     * delete id from cache
     * */
    public void deleteEmployee(String id){
        cache.asMap().remove(id);
    }
}
