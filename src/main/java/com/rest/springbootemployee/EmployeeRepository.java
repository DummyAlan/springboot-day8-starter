package com.rest.springbootemployee;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {
    List<Employee> employees;

    public EmployeeRepository() {
        employees = new ArrayList<>();
        employees.add(new Employee(1, "Lily", 20, "Female", 80000));
        employees.add(new Employee(2, "Desiree", 20, "Female", 80000));
        employees.add(new Employee(3, "Rafael", 20, "Male", 80000));
    }

    public List<Employee> findAll() {
        return employees;
    }

    public Employee findById(Integer id) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .orElseThrow(NoEmployeeFoundException::new);
    }

    public List<Employee> findByGender(String gender) throws NoEmployeeFoundException{
        return employees.stream()
                .filter(employee -> employee.getGender().equals(gender))
                .collect(Collectors.toList());
    }



    public Integer generateNextId() {
        int maxId = employees.stream()
                .mapToInt(employee -> employee.getId())
                .max()
                .orElse(1);
        return maxId + 1;
    }


    public void delete(Integer id) throws NoEmployeeFoundException{
        Employee existingEmployee = findById(id);
        employees.remove(existingEmployee);
    }

    public List<Employee> findByPage(int page, int pageSize) throws NoEmployeeFoundException{
        return employees.stream()
                .skip((long) (page - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public void clearAll(){
        employees.clear();
    }

    public Employee create(Employee employee) {
        Integer nextId = generateNextId();
        employee.setId(nextId);
        employees.add(employee);
        return employee;
    }
}
