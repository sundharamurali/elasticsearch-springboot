package com.poc.es.elasticsearchspringboot.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String jobTitle;
    private String phone;
    private Integer size;
}
