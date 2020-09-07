package com.EmployeesEvents.employeesEvents.dao;

import java.util.List;

import com.EmployeesEvents.employeesEvents.entity.Employees;
import com.EmployeesEvents.employeesEvents.exceptions.daoImplException.EmployeesDaoImplException;

public interface EmployeesDao {
	

	public boolean addEmployees(Employees emp) throws EmployeesDaoImplException;
	
	public boolean deleteEmployees(Employees emp) throws EmployeesDaoImplException;
	
	public boolean updateEmployees(Employees emp) throws EmployeesDaoImplException;
	
	public List getEmployees() throws EmployeesDaoImplException;
	
	public Employees getEmployeeByMid(String Mid) throws EmployeesDaoImplException;

}
