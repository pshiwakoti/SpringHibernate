package com.s3s.spring.dao;

import java.util.List;

import com.s3s.models.Customer;

/**
 * Customer CRUD operation
 * @author Prakash Shiwakoti
 * @since Jan 15, 2017
 */
public interface ICustomerDao {

	/**
	 * Adds customer to the database.
	 * @param customer to be added
	 * @return success or fail
	 */
	public boolean add(Customer customer);

	/**
	 * Updates the customer
	 * @param customer to be updated
	 * @return updated customer
	 */
	public Customer update(Customer customer);

	/**
	 * Deletes customer by id
	 * @param id to be deleted
	 * @return success or fail
	 */
	public boolean delete(int id);
	
	/**
	 * Gets all customer
	 * @return list of customer
	 */
	public List<Customer> getAll();
	
	/**
	 * Customer by id
	 * @param id of customer to be fetch
	 * @return customer
	 */
	public Customer getById(int id);
	
	/**
	 * add all customers at one shot
	 * @param customers to be added
	 * @return success or fail
	 */
	public boolean addAllCustomer(List<Customer> customers);
}
