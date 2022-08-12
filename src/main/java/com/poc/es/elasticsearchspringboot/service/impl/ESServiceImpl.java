package com.poc.es.elasticsearchspringboot.service.impl;

import com.poc.es.elasticsearchspringboot.connector.ESClientConnector;
import com.poc.es.elasticsearchspringboot.exception.RecordNotFoundException;
import com.poc.es.elasticsearchspringboot.model.Employee;
import com.poc.es.elasticsearchspringboot.service.ESService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ESServiceImpl implements ESService {

    @Autowired
    private ESClientConnector esClientConnector;

    @Override
    public Employee fetchEmployeeById(String id) throws RecordNotFoundException, IOException {
        return esClientConnector.fetchEmployeeById(id);
    }

    @Override
    public String insertEmployee(Employee employee) throws IOException {
        return esClientConnector.insertEmployee(employee);
    }

    @Override
    public boolean bulkInsertEmployees(List<Employee> employees) throws IOException {
       return esClientConnector.bulkInsertEmployees(employees);
    }

    @Override
    public List<Employee> fetchEmployeesWithMustQuery(Employee employee) throws IOException {
        return esClientConnector.fetchEmployeesWithMustQuery(employee);
    }

    @Override
    public List<Employee> fetchEmployeesWithShouldQuery(Employee employee) throws IOException {
        return esClientConnector.fetchEmployeesWithShouldQuery(employee);
    }

    @Override
    public String deleteEmployeeById(Long id) throws IOException {
        return esClientConnector.deleteEmployeeById(id);
    }

    @Override
    public String updateEmployee(Employee employee) throws IOException {
        return esClientConnector.updateEmployee(employee);
    }
}
