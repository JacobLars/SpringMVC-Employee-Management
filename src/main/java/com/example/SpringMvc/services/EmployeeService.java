
package com.example.SpringMvc.services;

import com.example.SpringMvc.models.Employee;
import com.example.SpringMvc.repositories.EmployeeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    
    @Autowired
    EmployeeRepository employeeRepository;
    
    public void saveEmployee(Employee employee){
        employeeRepository.save(employee);
    }
    
    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }
    
    public void deleteEmployee(int id){
        employeeRepository.deleteById(id);
    }
    
    public Employee findEmployeeById(int id){
       return employeeRepository.findById(id).get();
    }
    
    public List<Employee> getEmployeesByDepartment(String department){
        return employeeRepository.findEmployeeByDepartment(department);
    }
    public List<String> getDepartments(){
        return employeeRepository.getDistinctDepartments();
    }
}
