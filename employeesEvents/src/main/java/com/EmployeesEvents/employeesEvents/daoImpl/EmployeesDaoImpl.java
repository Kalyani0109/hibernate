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

import com.EmployeesEvents.employeesEvents.dao.EmployeesDao;
import com.EmployeesEvents.employeesEvents.entity.Employees;
import com.EmployeesEvents.employeesEvents.exceptions.daoImplException.EmployeesDaoImplException;

public class EmployeesDaoImpl implements EmployeesDao {
	
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
				System.out.println("zfvsdfvsdf");
				return this.sessionFactory.openSession();
			}
	}
	
	public void beginTransaction(Session session){
		if(!session.getTransaction().isActive()){
			session.beginTransaction();
		}
	}

	public boolean addEmployees(Employees emp) throws EmployeesDaoImplException {
		// TODO Auto-generated method stub
		try{
//			setup();
			Session session = getSession();
			beginTransaction(session);
			session.merge(emp);
			session.getTransaction().commit();
			session.close();			
			
			return true;
		}catch(HibernateException hbException){
			hbException.printStackTrace();
			throw new EmployeesDaoImplException("Error in adding Employee Please Try Again Later", hbException.getCause());
		}
	}

	public boolean deleteEmployees(Employees emp) throws EmployeesDaoImplException {
		// TODO Auto-generated method stub
		try{
//			setup();
			Session session = getSession();
			beginTransaction(session);
			session.delete(emp);
			session.getTransaction().commit();
			session.close();
			return true;
		}catch(HibernateException hbException){
			throw new EmployeesDaoImplException("Error in deleting Employee Please Try Again Later", hbException.getCause());
		}
	}

	public boolean updateEmployees(Employees emp) throws EmployeesDaoImplException {
		// TODO Auto-generated method stub
		try{
//			setup();
			Session session = getSession();
			beginTransaction(session);
			session.update(emp);
			session.getTransaction().commit();
			session.close();
			return true;
		}catch(HibernateException hbException){
			throw new EmployeesDaoImplException("Error in updating Employee Please Try Again Later", hbException.getCause());
		}
	}

	@Transactional(Transactional.TxType.REQUIRED)
	public List getEmployees() throws EmployeesDaoImplException {
		// TODO Auto-generated method stub
		try{
//			setup();
			Session session = getSession();
			beginTransaction(session);
			String select="FROM Employees";
			Query<Employees> query = session.createQuery(select, Employees.class);
//			session.getTransaction().commit();
			return query.getResultList();
			
		}catch(HibernateException hbException){
			hbException.printStackTrace();
			throw new EmployeesDaoImplException("Error in fetching Employee details", hbException.getCause());
		}catch(NoResultException noResultException){
			throw new EmployeesDaoImplException("No Registered Employees", noResultException.getCause());
		}catch(IndexOutOfBoundsException outOfBoundException){
			throw new EmployeesDaoImplException("No Registered Employees", outOfBoundException.getCause());
		}
	}

	@Transactional(Transactional.TxType.REQUIRED)
	public Employees getEmployeeByMid(String Mid) throws EmployeesDaoImplException {
		// TODO Auto-generated method stub
		try{
//			setup();
			Session session = getSession();
			beginTransaction(session);
			String select = "FROM Employees WHERE MID = :parameter";
			Query<Employees> query = session.createQuery(select, Employees.class);
			query.setParameter("parameter", Mid);
//			session.getTransaction().commit();
			return query.getSingleResult();
		}catch(HibernateException hbException){
			hbException.printStackTrace();
			throw new EmployeesDaoImplException("Error in fetching Employee details for the given employee id", hbException.getCause());
		}catch(NoResultException noResultException){
			throw new EmployeesDaoImplException("No Registered Employees for the given employee id", noResultException.getCause());
		}catch(IndexOutOfBoundsException outOfBoundException){
			throw new EmployeesDaoImplException("No Registered Employees foe the given employee id", outOfBoundException.getCause());
		}
	}

}
