package com.s3s.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.s3s.models.Customer;

public class CustomerMapper implements RowMapper<Customer>{

		
		@Override
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Customer customer = new Customer();
			customer.setId(rs.getInt("id"));
			customer.setFirstName(rs.getString("firstName"));
			customer.setLastName(rs.getString("lastName"));
			customer.setAddress(rs.getString("address"));
			customer.setEmail(rs.getString("email"));
			return customer;
		}
}
