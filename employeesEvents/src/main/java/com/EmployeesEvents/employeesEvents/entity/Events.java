package com.EmployeesEvents.employeesEvents.entity;

import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "EVENTS")
public class Events {
	
	@Id
	@Column(name = "event_id", unique = true, nullable = false)
	int eventId;
	
	@Column(name = "event_title", nullable = false)
	String eventTitle;
	
	@Column(name = "description")
	String description;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "events", cascade = CascadeType.PERSIST)
	Set<Employees> employees;

	public Set<Employees> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employees> employees) {
		this.employees = employees;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void printEmp(Set<Employees> emp){
		Iterator empIterator = emp.iterator();
		if(empIterator.hasNext()){
			System.out.println(empIterator.next());
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Events other = (Events) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (employees == null) {
			if (other.employees != null)
				return false;
		} else if (!employees.equals(other.employees))
			return false;
		if (eventId != other.eventId)
			return false;
		if (eventTitle == null) {
			if (other.eventTitle != null)
				return false;
		} else if (!eventTitle.equals(other.eventTitle))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String result =  "Events: \neventId=" + eventId + ", \neventTitle=" + eventTitle + ", \ndescription=" + description
				+ ", \nemployees (MID) =\n";
		Iterator<Employees> empIterator = employees.iterator();
		if(empIterator.hasNext()){
			result = result + empIterator.next().getMID() + '\n';
		}
		return result;
	}

}
