package com.EmployeesEvents.employeesEvents.exceptions.daoImplException;

import com.EmployeesEvents.employeesEvents.exceptions.EmployeesEventsException;

public class EmployeesDaoImplException extends EmployeesEventsException {

	private static final long serialVersionUID = 1L;
	
	public EmployeesDaoImplException(){
		super();
	}
	
	public EmployeesDaoImplException(String msg, Throwable cause){
		super(msg, cause);
	}
}
