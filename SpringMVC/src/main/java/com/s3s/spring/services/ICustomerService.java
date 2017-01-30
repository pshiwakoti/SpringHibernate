package com.s3s.spring.services;

import java.util.List;

import com.s3s.models.Customer;

/**
 * Service interface for customer.
 * @author Prakash Shiwakoti
 * @since Jan 15, 2017
 */
public interface ICustomerService {

	/**
	 * Add customer to the system using 
	 * this method
	 * @param customer to be added
	 * @return added or not 
	 */
	public boolean addCustomer(Customer customer);
	
	/**
	 * Update Customer
	 * @param customer to be updated
	 * @return updated customer
	 */
	public Customer updateCustomer(Customer customer);

	
	/**
	 * Gets customer by Id
	 * @param id of the customer, which is unique
	 * @return customer object
	 */
	public Customer getCustomerById(int id);
	
	
	/**
	 * Gets all customer
	 * @return list of customer
	 */
	public List<Customer> getAllCustomer();
	
	
	/**
	 * Deletes the customer with given id.
	 * @param id of customer to be deleted
	 * @return success or fail
	 */
	public boolean deleteCustomer(int id);
}
