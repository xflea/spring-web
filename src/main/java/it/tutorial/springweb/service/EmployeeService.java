package it.tutorial.springweb.service;

import java.util.List;

import it.tutorial.springweb.model.Employee;

public interface EmployeeService {
	
	public List<Employee> findAll();
	
	public void save(Employee employee);
	
	public Employee findById(Long id);
	
	public void delete(Long id);
	
	public List<Employee> findAllByRest();
	
}