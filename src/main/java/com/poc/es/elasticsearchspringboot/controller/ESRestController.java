package com.poc.es.elasticsearchspringboot.controller;

import com.poc.es.elasticsearchspringboot.exception.RecordNotFoundException;
import com.poc.es.elasticsearchspringboot.model.Employee;
import com.poc.es.elasticsearchspringboot.service.ESService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class ESRestController {


    @Autowired
    private ESService esService;

    @GetMapping("/index/{id}")
    public ResponseEntity<Employee> fetchEmployeeById(@PathVariable("id") String id) throws RecordNotFoundException, IOException {
        Employee employee = esService.fetchEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/index/fetchWithMust")
    public ResponseEntity<List<Employee>> fetchEmployeesWithMustQuery(@RequestBody Employee employeeSearchRequest) throws IOException {
        List<Employee> employees = esService.fetchEmployeesWithMustQuery(employeeSearchRequest);
        return ResponseEntity.ok(employees);
    }

    @PostMapping("/index/fetchWithShould")
    public ResponseEntity<List<Employee>> fetchEmployeesWithShouldQuery(@RequestBody Employee employeeSearchRequest) throws IOException {
        List<Employee> employees = esService.fetchEmployeesWithMustQuery(employeeSearchRequest);
        return ResponseEntity.ok(employees);
    }

    @PostMapping("/index")
    public ResponseEntity<String> insertRecords(@RequestBody Employee employee) throws IOException {
        String status = esService.insertEmployee(employee);
        return ResponseEntity.ok(status);
    }

    @PostMapping("/index/bulk")
    public ResponseEntity<String> bulkInsertEmployees(@RequestBody List<Employee> employees) throws IOException {
        boolean isSuccess = esService.bulkInsertEmployees(employees);
        if(isSuccess) {
            return ResponseEntity.ok("Records successfully ingested!");
        } else {
            return ResponseEntity.internalServerError().body("Oops! unable to ingest data");
        }
    }
}
