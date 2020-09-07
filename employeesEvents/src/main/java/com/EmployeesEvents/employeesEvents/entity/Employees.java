package com.EmployeesEvents.employeesEvents.entity;

import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "EMPLOYEES")
public class Employees {

	@Id
	@Column(name = "MID", unique = true, nullable = false)
	String MID;
	
	@Column (name = "name", nullable = false)
	String name;
	
	@Column(name = "join_date", nullable = false)
	String joinDate;
	
	@Column(name = "email_id", nullable = false)
	String emailId;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "Employees_Events", joinColumns = { @JoinColumn(name = "MID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "event_id", nullable = false, updatable = false) })
	Set<Events> events;

	public Set<Events> getEvents() {
		return events;
	}

	public void setEvents(Set<Events> events) {
		this.events = events;
	}

	public String getMID() {
		return MID;
	}

	public void setMID(String mID) {
		MID = mID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public void printEvents(Set<Events> events){
		Iterator eventIterator = events.iterator();
		if(eventIterator.hasNext()){
			System.out.println(eventIterator.next());
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
		Employees other = (Employees) obj;
		if (MID == null) {
			if (other.MID != null)
				return false;
		} else if (!MID.equals(other.MID))
			return false;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		if (events == null) {
			if (other.events != null)
				return false;
		} else if (!events.equals(other.events))
			return false;
		if (joinDate == null) {
			if (other.joinDate != null)
				return false;
		} else if (!joinDate.equals(other.joinDate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String result =  "Employees: \nMID=" + MID + ", \nname=" + name + ", \njoinDate=" + joinDate + ", \nemailId=" + emailId
				+ ", \neventsId=\n";
		Iterator<Events> eventIterator = events.iterator();
		while(eventIterator.hasNext()){
			result = result + eventIterator.next().getEventId() + '\n';
		}
		return result;
	}


}
