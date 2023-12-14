package it.tutorial.springweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.tutorial.springweb.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}