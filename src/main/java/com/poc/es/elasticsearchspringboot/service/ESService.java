package com.poc.es.elasticsearchspringboot.service;

import com.poc.es.elasticsearchspringboot.exception.RecordNotFoundException;
import com.poc.es.elasticsearchspringboot.model.Employee;

import java.io.IOException;
import java.util.List;

public interface ESService {

    public Employee fetchEmployeeById(String id) throws RecordNotFoundException, IOException;

    public String insertEmployee(Employee employee) throws IOException;

    public boolean bulkInsertEmployees(List<Employee> employees) throws IOException;

    public List<Employee> fetchEmployees(Employee employee) throws IOException;
}
