package it.tutorial.springweb.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

	@Override
	public List<Employee> findAllByRest() {
		ObjectMapper objectMapper = new ObjectMapper();
		List<Employee> employeesJsonList = null;
		URL url;
		
		try {
			url = new URL("http://localhost:8181/api/employees");
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			if(conn.getResponseCode() != 200)
				throw new RuntimeException("Failed: Http error code: " + conn.getResponseCode());
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
		    JSONTokener tokener = new JSONTokener(br);
		    JSONObject json = new JSONObject(tokener);
		    
		    String jsonString = json.toString();
		    
			conn.disconnect();
					
			employeesJsonList = objectMapper.readValue(jsonString, new TypeReference<List<Employee>>(){});
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return employeesJsonList;
		
	}

}