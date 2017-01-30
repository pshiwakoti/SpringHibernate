package com.s3s.spring.dao;



import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.s3s.models.Customer;

public class CustomerDao implements ICustomerDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	

	public void setSession(SessionFactory session) {
		this.sessionFactory = session;
	}

	/**
	 * {@inheritDoc}
	 */
	
	@Transactional
	@Override
	public boolean add(Customer customer) {
		boolean result = false;

		try {
			sessionFactory.getCurrentSession().save(customer);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	public Customer getById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Customer customer = session.get(Customer.class, id);
		return customer;
	}

	@Transactional
	@Override
	public Customer update(Customer customer) {
		Session session = sessionFactory.getCurrentSession();
		session.update(customer);
		return customer;
	}

	@Transactional
	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getAll() {
		Session session = sessionFactory.getCurrentSession();
		Query<Customer> query = session.createQuery("from Customer");
		return query.getResultList();
	}

	@Transactional
    @Override
    public boolean delete(int id) {
        boolean status = false;
        Session session = sessionFactory.getCurrentSession();
        try {
            Customer customer = session.get(Customer.class, id);
            session.delete(customer);
            status = true;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return status;
    }
    @Transactional
    @Override
    public boolean addAllCustomer(List<Customer> customers) {
        boolean result = false;
        if (customers == null || customers.size() == 0) {
            return false;
        }
        Session session = sessionFactory.getCurrentSession();
        for (int i = 0; i < customers.size(); i++) {
            session.save(customers.get(i));
            if (i % 20 == 0) { // 20, same as the JDBC batch size
                // flush a batch of inserts and release memory:
                session.flush();
                session.clear();
            }
            boolean isLastCustomer = customers.size() - 1 == i;
            if (isLastCustomer) {
                result = true;
                break;
            }
        }
        return result;
    }


}
