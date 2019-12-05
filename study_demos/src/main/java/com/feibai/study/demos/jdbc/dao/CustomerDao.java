package com.feibai.study.demos.jdbc.dao;

import java.util.List;

import com.feibai.study.demos.beans.Customer;

public interface CustomerDao {
	public void add(Customer c);

	public void update(Customer c);

	public void delete(Customer c);

	public Customer getCustomerById(int id);

	public List<Customer> queryAllCostomer();

}
