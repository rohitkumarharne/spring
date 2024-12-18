package com.web.tech.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.web.tech.model.Employee;



@Repository("repo")
public interface Repo extends CrudRepository<Employee, Integer> {

}
