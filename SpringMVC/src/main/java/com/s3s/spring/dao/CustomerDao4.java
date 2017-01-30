package com.s3s.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import com.s3s.models.Customer;

public class CustomerDao4 extends NamedParameterJdbcDaoSupport implements ICustomerDao {

	private static final String ADD_CUSTOMER = "insert into customer(firstName, lastName, address, email, age)"
			+ "values(:p,:q,:r,:s,:t)";
	private static final String DELETE_BY_ID = "DELETE FROM customer WHERE customer.id =";

	private DataSource dataSource;
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean add(Customer customer) {
		boolean result = false;

		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("p", customer.getFirstName());
		namedParameters.put("q", customer.getLastName());
		namedParameters.put("r", customer.getAddress());
		namedParameters.put("s", customer.getEmail());
		namedParameters.put("t", customer.getAge());
		try {
			getNamedParameterJdbcTemplate().update(ADD_CUSTOMER, namedParameters);
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
		String query = "select * from customer where id=:xyz";
		SqlParameterSource parameters = new MapSqlParameterSource().addValue("xyz", id);
		return getNamedParameterJdbcTemplate().queryForObject(query, parameters, Customer.class);
	}

	@Override
	public Customer update(Customer customer) {
		String query = "UPDATE customer SET firstName=:p, lastName=:q, address=:r, email=:s, age=:t WHERE customer.id =:u";
		SqlParameterSource parameters = new MapSqlParameterSource().addValue("p", customer.getFirstName())
				.addValue("q", customer.getLastName()).addValue("r", customer.getAddress())
				.addValue("s", customer.getEmail()).addValue("t", customer.getAge());
		return getNamedParameterJdbcTemplate().queryForObject(query, parameters, Customer.class);
	}

	@Override
	public List<Customer> getAll() {
		String query = "select * from customer";
		return getNamedParameterJdbcTemplate().query(query, new CustomerMapper());
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
