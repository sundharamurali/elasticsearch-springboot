package com.poc.es.elasticsearchspringboot.connector;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.*;
import com.poc.es.elasticsearchspringboot.exception.RecordNotFoundException;
import com.poc.es.elasticsearchspringboot.model.Employee;
import com.poc.es.elasticsearchspringboot.utils.QueryBuilderUtils;
import org.elasticsearch.client.RequestOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ESClientConnector {

    @Value("${elastic.index}")
    private String index;

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    public String insertEmployee(Employee employee) throws IOException {
        IndexRequest<Employee> request = IndexRequest.of(i->
                i.index(index)
                        .document(employee));
        IndexResponse response = elasticsearchClient.index(request);
        return response.result().toString();
    }

    public boolean bulkInsertEmployees(List<Employee> employeeList) throws IOException {
        BulkRequest.Builder builder = new BulkRequest.Builder();
        employeeList.stream().forEach(employee -> {
            builder.operations(op->
                    op.index(i->
                            i.index(index)
                                    .id(String.valueOf(employee.getId()))
                                    .document(employee)));
        });
        BulkResponse bulkResponse = elasticsearchClient.bulk(builder.build());
        return !bulkResponse.errors();
    }

   /* public void updateEmployee(Employee employee) {

        UpdateRequest request = UpdateRequest.of(r->
                r.index(index)
                        .id(String.valueOf(employee.getId()))
                        .doc(employee));
        elasticsearchClient.update(request, Employee.class, Employee.class);

    }*/

    public Employee fetchEmployeeById(String id) throws RecordNotFoundException, IOException {
        GetResponse<Employee> response = elasticsearchClient.get(req->
                req.index(index)
                        .id(id),Employee.class);
        if(!response.found())
            throw new RecordNotFoundException("Employee with ID" + id + " not found!");

        return response.source();
    }

    public List<Employee> fetchEmployees(Employee employee) throws IOException {
        List<Query> queries = prepareQueryList(employee);
        SearchResponse<Employee> employeeSearchResponse = elasticsearchClient.search(req->
                req.index(index)
                        .query(query->
                                query.bool(bool->
                                        bool.must(queries))), Employee.class);

        List<Employee> employeeList = employeeSearchResponse.hits().hits().stream().map(hit->hit.source()).collect(Collectors.toList());
        return employeeList;
    }

    private List<Query> prepareQueryList(Employee employee) {
        List<Query> queries = Arrays.asList(
                QueryBuilderUtils.termQuery("firstName", employee.getFirstName()),
                QueryBuilderUtils.termQuery("lastName", employee.getLastName()),
                QueryBuilderUtils.termQuery("gender", employee.getGender())

        );
        return queries;
    }

}
