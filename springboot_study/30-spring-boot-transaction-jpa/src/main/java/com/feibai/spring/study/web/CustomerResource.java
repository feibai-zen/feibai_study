package com.feibai.spring.study.web;

import com.feibai.spring.study.domain.Customer;
import com.feibai.spring.study.service.CustomerServiceTxInAnnotation;
import com.feibai.spring.study.service.CustomerServiceTxInCode;
import com.feibai.spring.study.dao.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerResource {
  private static final Logger LOG = LoggerFactory.getLogger(CustomerResource.class);

  @Autowired
  private CustomerServiceTxInAnnotation customerService;
  @Autowired
  private CustomerServiceTxInCode customerServiceInCode;
  @Autowired
  private CustomerRepository customerRepository;

  @PostMapping("/annotation")
  public Customer createInAnnotation(@RequestBody Customer customer) {
    LOG.info("CustomerResource create in annotation create customer:{}", customer.getUsername());
    return customerService.create(customer);
  }

  @PostMapping("/code")
  public Customer createInCode(@RequestBody Customer customer) {
    LOG.info("CustomerResource create in code create customer:{}", customer.getUsername());
    return customerServiceInCode.create(customer);
  }

  @GetMapping("")
  public List<Customer> getAll() {
    return customerRepository.findAll();
  }

}
