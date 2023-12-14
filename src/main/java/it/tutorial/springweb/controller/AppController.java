package it.tutorial.springweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.tutorial.springweb.model.Employee;
import it.tutorial.springweb.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class AppController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/list")
	public String findAllEmployee(Model model) {
		List<Employee> listEmployees = employeeService.findAll();
		
		model.addAttribute("employees", listEmployees);
		
		return "list-employees";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		model.addAttribute("vengoDa", "add");
		
		return "employee-form";
	}
	
	@GetMapping("/showFormForUpdate")
	public String updateForUpdate(@RequestParam("employeeId") Long id, Model model) {
		Employee employee = employeeService.findById(id);
		
		if(employee != null) {
			model.addAttribute("employee", employee);
			model.addAttribute("vengoDa", "update");
			return "employee-form";
		}
		else {
			model.addAttribute("messaggio", "Employee non trovato");
		}
		
		return null;
	}
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		employeeService.save(employee);
		
		return "redirect:/employees/list";
	}
	
//	@DeleteMapping("/delete")
//	public String deleteEmployee() {
//		
//	}

}