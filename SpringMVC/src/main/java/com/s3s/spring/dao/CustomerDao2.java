package com.s3s.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import com.s3s.models.Customer;

public class CustomerDao2 implements ICustomerDao {

	private static final String ADD_CUSTOMER = "insert into customer(firstName, lastName, address, email, age)"
			+ "values(?,?,?,?,?)";
	private static final String DELETE_BY_ID = "DELETE FROM customer WHERE customer.id =";

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean add(Customer customer) {
		boolean result = false;
		try {
			jdbcTemplate.update(ADD_CUSTOMER, customer.getFirstName(), customer.getLastName(), customer.getAddress(),
					customer.getEmail(), customer.getAge());
			result = true;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Customer getById(int id) {
		String query = "select * from customer where id=" + id;
		return jdbcTemplate.queryForObject(query, new CustomerMapper());
	}

	@Override
	public Customer update(Customer customer) {
		String query = "UPDATE customer SET firstName=?, lastName=?, address=?, email=?, age=? WHERE customer.id = ?";
		try {
			jdbcTemplate.update(query, customer.getFirstName(), customer.getLastName(), customer.getAddress(),
					customer.getEmail(), customer.getAge(), customer.getId());
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return customer;
	}

	@Override
	public List<Customer> getAll() {
		String query = "select * from customer";
		return jdbcTemplate.query(query, new CustomerMapper());
	}

	@Override
	public boolean delete(int id) {
		boolean status = false;
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement ps = connection.prepareStatement(DELETE_BY_ID + id);
			ps.executeUpdate();
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
			status = false;
		}
		return status;
	}

	@Override
	public boolean addAllCustomer(List<Customer> customers) {
		boolean result = false;

		try {
			Statement stmt = dataSource.getConnection().createStatement();
			List<String> allQuery = getAllBatchQuery(customers);
			for (String query : allQuery) {
				stmt.addBatch(query);
			}
			stmt.executeBatch();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private List<String> getAllBatchQuery(List<Customer> customers) {
		List<String> queryList = new ArrayList<String>();
		for (Customer customer : customers) {
			String sql = "insert into customer(firstName,lastName,email,address,age) values(" + "\'"
					+ customer.getFirstName() + "\'," + "\'" + customer.getLastName() + "\'," + "\'"
					+ customer.getEmail() + "\'," + "\'" + customer.getAddress() + "\'," + "\'" + customer.getAge()
					+ "\'" + ")";
			queryList.add(sql);
		}
		return queryList;
	}

}
