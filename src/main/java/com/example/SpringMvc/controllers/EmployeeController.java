package com.example.SpringMvc.controllers;

import com.example.SpringMvc.models.Employee;
import com.example.SpringMvc.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes("employee")
@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees")
    public String display(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("employeeList", employeeService.getEmployees());
        model.addAttribute("departments", employeeService.getDepartments());
        return "index";
    }

    @PostMapping("/employees/save")
    public String saveEmployee(Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/employees/delete/{employeeId}")
    public String deleteEmployee(@PathVariable("employeeId") int id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }

    @GetMapping("/employees/edit/{id}")
    public String showEditEmployeePage(@PathVariable("id") int id, Model model) {
        Employee employee = employeeService.findEmployeeById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("pageTitle", "Edit Employee " + "'" + employee.getName() + "'" + " (ID: " + employee.getId() + ")");
        return "edit_employee";
    }

    @GetMapping("/employees/department")
    public String getEmployeesByDepartment(@RequestParam(name = "department", required = false) String department, Model model) {

        if (department == null || department.isEmpty()) {
            model.addAttribute("employeeList", employeeService.getEmployees());
        } else {
            model.addAttribute("employeeList", employeeService.getEmployeesByDepartment(department));
        }
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", employeeService.getDepartments());
        return "index";

    }

}
