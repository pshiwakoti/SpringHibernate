package com.s3s.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.s3s.models.Customer;
import com.s3s.spring.services.ICustomerService;

@Controller
public class CustomerController {

	@Autowired
	ICustomerService customerService;

	public void setCustomerService(ICustomerService customerService) {
		this.customerService = customerService;
	}

	@RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
	public String getCustomer(@PathVariable int id, Model model) {
		Customer c = customerService.getCustomerById(id);
		model.addAttribute("cust", c);
		return "customer";
	}

	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	public ModelAndView getCustomers() {
		List<Customer> customers = customerService.getAllCustomer();
		ModelAndView mv = new ModelAndView("customers", "customerList", customers);
		return mv;
	}

	@RequestMapping(value = "/customers/json", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, headers = "Accept=application/json, application/xml")
	@ResponseBody
	public String getCustomerAsJson() {

		List<Customer> customers = customerService.getAllCustomer();
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonData = "";
		try {
			jsonData = objectMapper.writeValueAsString(customers);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonData;
	}
	
	
	@RequestMapping(value = "/customer", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, headers = "Accept=application/json, application/xml")
	@ResponseBody
	public String addCutomerAsJson(@RequestBody Customer customer) {
		customerService.addCustomer(customer);
		List<Customer> customers = customerService.getAllCustomer();
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonData = "";
		try {
			jsonData = objectMapper.writeValueAsString(customers);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonData;
	}
	
	
	//that can accepts list customer and submit to the database.
}
