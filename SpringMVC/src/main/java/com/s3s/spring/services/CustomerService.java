package com.s3s.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.s3s.models.Customer;
import com.s3s.spring.dao.ICustomerDao;

public class CustomerService implements ICustomerService {

	@Autowired
	ICustomerDao customerDao;
	
	public void setCustomerDao(ICustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addCustomer(Customer customer) {
		return customerDao.add(customer);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Customer updateCustomer(Customer customer) {
		return customerDao.update(customer);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Customer getCustomerById(int id) {
		return customerDao.getById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Customer> getAllCustomer() {
		return customerDao.getAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteCustomer(int id) {
		return customerDao.delete(id);
	}

}
