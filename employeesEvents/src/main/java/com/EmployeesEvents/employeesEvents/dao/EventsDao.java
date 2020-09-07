package com.EmployeesEvents.employeesEvents.dao;

import java.util.List;

import com.EmployeesEvents.employeesEvents.entity.Events;
import com.EmployeesEvents.employeesEvents.exceptions.daoImplException.EventsDaoImplException;

public interface EventsDao {
	public boolean creatEvents(Events event) throws EventsDaoImplException;
	
	public boolean deleteEvent( Events event) throws EventsDaoImplException;
	
	public boolean updateEvent(Events event) throws EventsDaoImplException;
	
	public List getEvents() throws EventsDaoImplException;
	
	public Events getEventById(int eventId) throws EventsDaoImplException;

}
