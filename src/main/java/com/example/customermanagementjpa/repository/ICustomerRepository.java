package com.example.customermanagementjpa.repository;

import com.example.customermanagementjpa.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface ICustomerRepository extends CrudRepository<Customer, Long> {
}
