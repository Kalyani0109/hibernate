package com.EmployeesEvents.employeesEvents.exceptions;

public class EmployeesEventsException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public EmployeesEventsException(){
		super();
	}
	
	public EmployeesEventsException(String msg, Throwable arg1){
		super(msg, arg1);
	}

}
