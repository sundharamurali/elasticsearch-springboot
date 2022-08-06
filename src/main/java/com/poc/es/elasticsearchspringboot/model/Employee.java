package com.poc.es.elasticsearchspringboot.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String jobTitle;
    private String phone;
}
