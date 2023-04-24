
package com.example.SpringMvc.repositories;

import com.example.SpringMvc.models.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    List<Employee> findEmployeeByDepartment(String department);
    
    @Query("SELECT DISTINCT e.department FROM Employee e")
    List<String> getDistinctDepartments();
}
