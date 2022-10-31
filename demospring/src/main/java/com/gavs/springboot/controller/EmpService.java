package com.gavs.springboot.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.gavs.springboot.model.Employee;
import com.gavs.springboot.model.EmployeeDAO;
import com.gavs.springboot.model.EmployeeRepository;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class EmpService {
	@Autowired
	private EmployeeRepository repo;
	
	@RequestMapping("/hello")
    public String welcomepage() {
        return "hello to Spring REST Controller";
    }
//     @RequestMapping(value="/findEmployee",method= RequestMethod.POST)
//     public Employee homepage(@RequestBody Employee emp) throws SQLException  {
//         EmployeeDAO empd=new EmployeeDAO();
//         return empd.findEmployeeById(emp);
//     }
	 @RequestMapping(value="/findEmployee",method= RequestMethod.GET)
	 public Employee find(int id) {
		 Optional<Employee> found=repo.findById(id);
		 Employee emp=found.get();
		 System.out.println(emp.getId());
		 return emp;
    }
//     @RequestMapping(value="/addEmployee",method= RequestMethod.POST)
//     public int addEmployee(@RequestBody Employee emp) {
//     	EmployeeDAO empdao=new EmployeeDAO();
//     	return empdao.addEmployee(emp);
//     }
	@RequestMapping(value="/addEmployee",method= RequestMethod.POST)
	 public void addEmployee(@RequestBody Employee emp) {
		 repo.save(emp);
	 }
//     @RequestMapping(value="/updateEmployee",method= RequestMethod.PUT)
//     public int modifyEmployee(@RequestBody Employee emp) {
//     	EmployeeDAO empd=new EmployeeDAO();
//         return empd.modifyEmployee(emp);
//   }
	 @RequestMapping(value="/updateEmployee",method= RequestMethod.PUT)
    public Employee modifyEmployee(@RequestBody Employee emp) {
		return repo.save(emp);
    }
//     @RequestMapping(value="/removeEmployee",method= RequestMethod.DELETE)
//     public int removeEmployee(@RequestBody    Employee emp) {
//         EmployeeDAO emp2 = new EmployeeDAO();
//         return emp2.deleteEmployee(emp);
//     }
	@RequestMapping(value="/removeEmployee",method= RequestMethod.DELETE)
    public int removeEmployee(@RequestBody Employee emp) {
    	 repo.deleteById(emp.getId());	
		// System.out.println(emp.getId());
		 return 1;
    }    
   

