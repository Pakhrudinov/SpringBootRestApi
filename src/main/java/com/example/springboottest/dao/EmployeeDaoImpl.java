package com.example.springboottest.dao;


import com.example.springboottest.entity.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDAO {

    @Autowired
    private EntityManager entityManager;


    @Override
    public List<Employee> getAllEmployees() {

        List<Employee> allEmployees = entityManager.createQuery("from Employee ", Employee.class)
                .getResultList();

        return allEmployees;
    }

    @Override
    public void saveEmployee(Employee employee) {

        Employee newEmployee1 =  entityManager.merge(employee);
        employee.setId(newEmployee1.getId());
    }

    @Override
    public Employee getEmployee(int id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public void deleteEmployee(int id) {
        Query query = entityManager.createQuery("delete from Employee " +
                "where id = :employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }
}
