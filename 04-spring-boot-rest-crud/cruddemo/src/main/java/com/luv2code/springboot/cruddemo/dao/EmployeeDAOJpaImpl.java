package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

    // define field for entitymanager
    private EntityManager entityManager;

    // set up constructor injection
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {

        // create a query
        TypedQuery<Employee> query = this.entityManager.createQuery("from Employee", Employee.class);

        // execute query and get result list
        List<Employee> employeeList = query.getResultList();

        // return the results
        return employeeList;
    }

    @Override
    public Employee findById(int id) {
        // get employee
        Employee employee = this.entityManager.find(Employee.class, id);

        // return employee
        return employee;
    }

    @Override
    public Employee save(Employee employee) {
        // save employee
        Employee newEmployee = this.entityManager.merge(employee);

        // return employee
        return newEmployee;
    }

    @Override
    public void deleteById(int id) {
        // find employee by id
        Employee employee = this.entityManager.find(Employee.class, id);

        // remove employee
        this.entityManager.remove(employee);
    }
}