package com.web.tech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.web.tech.model.Employee;
import com.web.tech.repo.Repo;

@Service("empser")
@CrossOrigin("http://localhost:3000/")
public class Empsarvice {

	@Autowired
	Repo repo;

	public boolean isSaveData(Employee e) {
		System.err.println(e);
		repo.save(e);
		return true;
	}

	List<Employee> list;

	public List<Employee> getallEmployees() {
		list = (List<Employee>) repo.findAll();
		
		return list;
	}
	
	public boolean deleteemp(int id)
	{
		repo.deleteById(id);
		return true;
	}
	public void upadterecodebyId(Employee employee)
	{
		repo.save(employee);
	}
}
