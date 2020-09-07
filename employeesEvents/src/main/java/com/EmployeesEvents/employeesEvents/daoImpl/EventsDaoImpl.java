package com.EmployeesEvents.employeesEvents.daoImpl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import com.EmployeesEvents.employeesEvents.dao.EventsDao;
import com.EmployeesEvents.employeesEvents.entity.Events;
import com.EmployeesEvents.employeesEvents.exceptions.daoImplException.EventsDaoImplException;

public class EventsDaoImpl implements EventsDao {
	
protected static SessionFactory sessionFactory;
	
static 
{
	final StandardServiceRegistry registry= new StandardServiceRegistryBuilder().configure().build();
 try 
 {
  // Create the SessionFactory from hibernate.cfg.xml
  sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
 } 
 catch (Exception e) 
 {
	 e.printStackTrace();
	StandardServiceRegistryBuilder.destroy(registry);
 }
}
//	public void setup(){
//		final StandardServiceRegistry registry= new StandardServiceRegistryBuilder().configure().build();
//		
//		try{
//			sessionFactory= new MetadataSources(registry).buildMetadata().buildSessionFactory();
//		}catch(Exception e){
//			e.printStackTrace();
//			StandardServiceRegistryBuilder.destroy(registry);
//		}
//	}
	
	
	public Session getSession(){
		if (this.sessionFactory.getCurrentSession() != null)
			return this.sessionFactory.getCurrentSession();
		else{
			
			System.out.println("adfvfdvbaff");
			return this.sessionFactory.openSession();
		}
	}
	
	public void beginTransaction(Session session){
		if(!session.getTransaction().isActive()){
			session.beginTransaction();
		}
	}

	public boolean creatEvents(Events event) throws EventsDaoImplException {
		// TODO Auto-generated method stub
		try{
//			setup();
			Session session = getSession();
			beginTransaction(session);
			session.merge(event);
			session.getTransaction().commit();
			session.close();			
			
			return true;
		}catch(HibernateException hbException){
			throw new EventsDaoImplException("Error in creating new user", hbException.getCause());
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteEvent(Events event) throws EventsDaoImplException {
		// TODO Auto-generated method stub
		try{
//			setup();
			Session session = getSession();
			beginTransaction(session);
			session.delete(event);
			session.getTransaction().commit();
			session.close();
			return true;
		}catch(HibernateException hbException){
			throw new EventsDaoImplException("Error in deleting Event", hbException.getCause());
		}
	}

	public boolean updateEvent(Events event) throws EventsDaoImplException {
		// TODO Auto-generated method stub
		try{
//			setup();
			Session session = getSession();
			beginTransaction(session);
			session.update(event);
			session.getTransaction().commit();
			session.close();
			return true;
		}catch(HibernateException hbException){
			throw new EventsDaoImplException("Error in fetching Event details", hbException.getCause());
		}
	}
	
	@Transactional(Transactional.TxType.REQUIRED)
	public List getEvents() throws EventsDaoImplException {
		// TODO Auto-generated method stub
		try{
//			setup();
			Session session = getSession();
			beginTransaction(session);
			String select="FROM Events";
			Query<Events> query = session.createQuery(select, Events.class);
//			session.getTransaction().commit();
			return query.getResultList();
			
		}catch(HibernateException hbException){
			hbException.printStackTrace();
			throw new EventsDaoImplException("Error in fetching Event details", hbException.getCause());
		}catch(NoResultException noResultException){
			throw new EventsDaoImplException("No Events Registered", noResultException.getCause());
		}catch(IndexOutOfBoundsException outOfBoundException){
			throw new EventsDaoImplException("No Events Registered", outOfBoundException.getCause());
		}
	}

	@Transactional(Transactional.TxType.REQUIRED)
	public Events getEventById(int eventId) throws EventsDaoImplException {
		// TODO Auto-generated method stub
		try{
//			setup();
			Session session = getSession();
			beginTransaction(session);
			String select = "FROM Events WHERE eventId = :parameter";
			Query<Events> query = session.createQuery(select, Events.class);
			query.setParameter("parameter", eventId);
//			session.getTransaction().commit();
			return query.getSingleResult();
		}catch(HibernateException hbException){
			hbException.printStackTrace();
			throw new EventsDaoImplException("Error in fetching Event details for the given event Id", hbException.getCause());
		}catch(NoResultException noResultException){
			throw new EventsDaoImplException("No Events Registered for the given event id", noResultException.getCause());
		}catch(IndexOutOfBoundsException outOfBoundException){
			throw new EventsDaoImplException("No Events Registered for the given event id", outOfBoundException.getCause());
		}
	}

}
