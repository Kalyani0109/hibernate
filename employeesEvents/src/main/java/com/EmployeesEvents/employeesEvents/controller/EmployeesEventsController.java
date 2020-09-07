package com.EmployeesEvents.employeesEvents.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.EmployeesEvents.employeesEvents.dao.EmployeesDao;
import com.EmployeesEvents.employeesEvents.dao.EventsDao;
import com.EmployeesEvents.employeesEvents.daoImpl.EmployeesDaoImpl;
import com.EmployeesEvents.employeesEvents.daoImpl.EventsDaoImpl;
import com.EmployeesEvents.employeesEvents.entity.Employees;
import com.EmployeesEvents.employeesEvents.entity.Events;
import com.EmployeesEvents.employeesEvents.exceptions.EmployeesEventsException;



public class EmployeesEventsController {

public static void main (String[] args){
		
		Scanner input = new Scanner(System.in);
		Employees emp = new Employees();
		EventsDao eventDao = new EventsDaoImpl();
		Events event = new Events();
		EmployeesDao empDao = new EmployeesDaoImpl();
		
		while(true){
			System.out.println("1. Register Employees For Event \n 2. Display All Employees \n3. Register Events \n4. Display All Events \n 5. Exit");
			int choice = input.nextInt();
			int eventId; int x;
			
			switch(choice){
			
			case 1: System.out.println("Register the employee");
				System.out.println("Enter Employee MID");
				input.nextLine();
				String empInput = input.nextLine();
				emp.setMID(empInput);
				System.out.println("Enter employee name");
				empInput = input.nextLine();
				emp.setName(empInput);
				System.out.println("Enter employee joining date in DD/MM/YYYY");
				empInput = input.nextLine();
				emp.setJoinDate(empInput);
				System.out.println("Enter employee email Id");
				empInput = input.nextLine();
				emp.setEmailId(empInput);
				System.out.println("enter no of events registered to employee");
				Set<Events> eventSet = new HashSet<Events>();
				x = input.nextInt();
				for(int i=0;i<x;i++){
					System.out.println("Enter event Id registered");
					eventId = input.nextInt();
					try{
						event = eventDao.getEventById(eventId);	
						eventSet.add(event);
					}catch(EmployeesEventsException exception){
						exception.printStackTrace();
						System.out.println(exception.getMessage() + exception.getCause());
					}
				}
				System.out.println(eventSet);
				emp.setEvents(eventSet);
				try{
					empDao.addEmployees(emp);
					System.out.println("Employee sucessfully registered");
				}catch(EmployeesEventsException exception){
					exception.printStackTrace();
					System.out.println(exception.getMessage() + exception.getCause());
				}
				break;
			
			case 2: System.out.println("Display all the employee");
				List<Employees> empList = new ArrayList<Employees>();
				try{
					empList = empDao.getEmployees();
				}catch(EmployeesEventsException exception){
					exception.printStackTrace();
					System.out.println(exception.getMessage() + exception.getCause());
				}
				for(Employees employee: empList){
					System.out.println(employee.toString());
				}
				break;
				
			case 3: System.out.println("Register Events");
				System.out.println("Enter Event Id");
				eventId = input.nextInt();
				event.setEventId(eventId);
				System.out.println("Enter event title");
				input.nextLine();
				String eventTitle = input.nextLine();
				event.setEventTitle(eventTitle);
				System.out.println("Enter event description");
				input.nextLine();
				String eventDes = input.nextLine();
				event.setDescription(eventDes);
				System.out.println("enter no of employees registered to event");
				Set<Employees> empSet = new HashSet<Employees>();
				x = input.nextInt();
				for(int i=0;i<x;i++){
					System.out.println("Enter employee Mid");
					input.nextLine();
					String empId = input.nextLine();
					try{
						Employees emps = empDao.getEmployeeByMid(empId);
						empSet.add(emps);
					}catch(EmployeesEventsException exception){
						exception.printStackTrace();
						System.out.println(exception.getMessage() + exception.getCause());
					}
				}
				System.out.println(empSet);
				event.setEmployees(empSet);
				try{
					eventDao.creatEvents(event);
					System.out.println("Event sucessfully registered");
				}catch(EmployeesEventsException exception){
					exception.printStackTrace();
					System.out.println(exception.getMessage() + exception.getCause());
				}
				break;
			case 4: System.out.println("Display Events");
				List<Events> eventList = new ArrayList<Events>();
				try{
					eventList = eventDao.getEvents();
				}catch(EmployeesEventsException exception){
					exception.printStackTrace();
					System.out.println(exception.getMessage() + exception.getCause());
				}
				for(Events events: eventList){
					System.out.println(events.toString());
				}
				break;
				
			case 5: System.exit(0);
				
			default: System.out.println("Wrong Choice \n Please Try Again");
			}
			
		}
	}

}
