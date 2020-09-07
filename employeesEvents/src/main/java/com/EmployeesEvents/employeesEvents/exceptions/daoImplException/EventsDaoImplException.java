package com.EmployeesEvents.employeesEvents.exceptions.daoImplException;

import com.EmployeesEvents.employeesEvents.exceptions.EmployeesEventsException;

public class EventsDaoImplException extends EmployeesEventsException {
	
	private static final long serialVersionUID = 1L;
	
	public EventsDaoImplException(){
		super();
	}
	
	public EventsDaoImplException(String msg, Throwable cause){
		super(msg, cause);
	}

}
