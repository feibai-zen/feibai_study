package com.feibai.spring.study.service;

import com.feibai.spring.study.domain.Customer;
import com.feibai.spring.study.dao.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerServiceTxInAnnotation {

  private static final Logger LOG = LoggerFactory.getLogger(CustomerServiceTxInAnnotation.class);

  @Autowired
  private CustomerRepository customerRepository;

  @Transactional
  public Customer create(Customer customer) {
    LOG.info("CustomerService In Annotation create customer:{}", customer.getUsername());
    if (customer.getId() != null) {
      throw new RuntimeException("用户已经存在");
    }
    customer.setUsername("Annotation:" + customer.getUsername());
    return customerRepository.save(customer);
  }
}
