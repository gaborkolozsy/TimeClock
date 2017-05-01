/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.service.impl;

import hu.gaborkolozsy.timeclock.DevelopmentTest;
import hu.gaborkolozsy.timeclock.model.Customer;
import hu.gaborkolozsy.timeclock.service.CustomerService;
import hu.gaborkolozsy.timeclock.service.PayService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

/**
 * Test {@code CustomerServiceImpl} class and part of {@code Customer} data 
 * access layer through with it.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 */
public class CustomerServiceImplTest extends DevelopmentTest {

    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private PayService payService;
    
    /**
     * Test of getByCustomerId method, of class CustomerServiceImpl.
     */
    @Test
    public void testGetByCustomerId() {
        Customer customer = customerService.getByCustomerId(valid);
        assertNotNull(isNull, customer);
        message = "Identifiers not equals!";
        assertEquals(message, valid, customer.getCustomerId());
    }

    /**
     * Test of getByCustomerName method, of class CustomerServiceImpl.
     */
    @Test
    public void testGetByCustomerName() {
        String name = "Company" + valid;
        Customer customer = customerService.getByCustomerName(name);        
        assertNotNull(isNull, customer);
        message = "Name is not " + name + valid + "!";
        assertEquals(message, name, customer.getName());
    }

    /**
     * Test of updateContactByCustomerId method, of class CustomerServiceImpl.
     */
    @Test
    public void testUpdateContactByCustomerId() {
        Customer customer = customerService.getByCustomerId(valid);
        assertNotNull(isNull, customer);
        String contact = "Secretary";
        message = "Contact is not " + contact + "!";
        assertEquals(message, contact, customer.getContact());        
        
        contact = "Updated";
        customerService.updateContactByCustomerId(valid, contact);        
        customer = customerService.getByCustomerId(valid);
        assertNotNull(isNull, customer);
        assertEquals(message, contact, customer.getContact());
    }

    /**
     * Test of removeByCustomerId method, of class CustomerServiceImpl.
     */
    @Test
    public void testRemoveByCustomerId() {
        Customer customer = customerService.getByCustomerId(valid);
        assertNotNull(isNull, customer);
        
        payService.removeByPayId(PAYID + valid); // constraint
        customerService.removeByCustomerId(valid);
        result = customerService.isExistEntity(customer);
        message = "Customer is exist!";
        assertFalse(message, result);
    }

    /**
     * Test of isExistWithCustomerId method, of class CustomerServiceImpl.
     */
    @Test
    public void testIsExistWithCustomerId() {
        Customer customer = customerService.getByCustomerId(valid);
        assertNotNull(isNull, customer);
        
        result = customerService.isExistWithCustomerId(valid);
        message = "Customer is not exist!";
        assertTrue(message, result);
        
        result = customerService.isExistWithCustomerId(invalid);
        message = "Customer is exist!";
        assertFalse(message, result);
    }
    
    /**
     * Test throws exception by non exist ID.
     */
    @Test
    public void testThrowsExceptionWhenIdIsNotExist() {
        message = "Throws exception test is not ok!";
        assertEquals(message, "OK", 
                new ExceptionVerifier(() -> customerService.getByCustomerId(invalid))
                .isThrowing(EmptyResultDataAccessException.class));
    }

}