package it.tutorial.springweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.tutorial.springweb.model.Employee;
import it.tutorial.springweb.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public void save(Employee employee) {
		employeeRepository.save(employee);
	}

	@Override
	public Employee findById(Long id) {
		Optional<Employee> opt = employeeRepository.findById(id);
		
		if(opt.isPresent()) return opt.get();
		
		return null;
	}

	@Override
	public void delete(Long id) {
		Optional<Employee> opt = employeeRepository.findById(id);
		
		if(opt.isPresent()) employeeRepository.deleteById(id);
	}

}