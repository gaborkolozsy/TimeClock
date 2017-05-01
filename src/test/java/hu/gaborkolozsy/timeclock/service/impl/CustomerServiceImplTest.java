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
        Customer customer = customerService.getByCustomerId(VALID);
        assertNotNull(ISNULL, customer);
        message = "Identifiers not equals!";
        assertEquals(message, VALID, customer.getCustomerId());
    }

    /**
     * Test of getByCustomerName method, of class CustomerServiceImpl.
     */
    @Test
    public void testGetByCustomerName() {
        String name = "Company" + VALID;
        Customer customer = customerService.getByCustomerName(name);        
        assertNotNull(ISNULL, customer);
        message = "Name is not " + name + VALID + "!";
        assertEquals(message, name, customer.getName());
    }

    /**
     * Test of updateContactByCustomerId method, of class CustomerServiceImpl.
     */
    @Test
    public void testUpdateContactByCustomerId() {
        Customer customer = customerService.getByCustomerId(VALID);
        assertNotNull(ISNULL, customer);
        String contact = "Secretary";
        message = "Contact is not " + contact + "!";
        assertEquals(message, contact, customer.getContact());        
        
        contact = "Updated";
        customerService.updateContactByCustomerId(VALID, contact);        
        customer = customerService.getByCustomerId(VALID);
        assertNotNull(ISNULL, customer);
        assertEquals(message, contact, customer.getContact());
    }

    /**
     * Test of removeByCustomerId method, of class CustomerServiceImpl.
     */
    @Test
    public void testRemoveByCustomerId() {
        Customer customer = customerService.getByCustomerId(VALID);
        assertNotNull(ISNULL, customer);
        
        payService.removeByPayId(PAYID + VALID); // constraint
        customerService.removeByCustomerId(VALID);
        result = customerService.isExistEntity(customer);
        message = "Customer is exist!";
        assertFalse(message, result);
    }

    /**
     * Test of isExistWithCustomerId method, of class CustomerServiceImpl.
     */
    @Test
    public void testIsExistWithCustomerId() {
        Customer customer = customerService.getByCustomerId(VALID);
        assertNotNull(ISNULL, customer);
        
        result = customerService.isExistWithCustomerId(VALID);
        message = "Customer is not exist!";
        assertTrue(message, result);
        
        result = customerService.isExistWithCustomerId(INVALID);
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
                new ExceptionVerifier(() -> customerService.getByCustomerId(INVALID))
                .isThrowing(EmptyResultDataAccessException.class));
    }

}