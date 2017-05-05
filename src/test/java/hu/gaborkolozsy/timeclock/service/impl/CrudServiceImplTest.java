/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.service.impl;

import hu.gaborkolozsy.timeclock.dao.CrudDao;
import hu.gaborkolozsy.timeclock.model.Customer;
import hu.gaborkolozsy.timeclock.model.Customer.CustomerBuilder;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.ArgumentMatchers.any;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

/**
 * Test {@code CrudServiceImpl} class common methods with {@code  Mockito}.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 */
@RunWith(MockitoJUnitRunner.class)
public class CrudServiceImplTest {

    @InjectMocks
    private CrudServiceImpl crudService;
    
    @Mock
    private CrudDao crudDao;
    
    /**
     * Test of save method, of class CrudServiceImpl.
     */
    @Test
    public void testSave() {
        Customer customer = new Customer();
        final String[] message = new String[]{"", " saved!"};
        final int[] saved = new int[1];
        
        doNothing().when(crudDao).save(customer);
        crudService.save(customer);
        
        assertEquals("Something wrong!", 0, saved[0]);
        
        doAnswer((Answer<String>) new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                message[0] = customer.getClass().getSimpleName() + message[1];
                saved[0]++;
                return message[0];
            }
        }).doNothing()
        .doAnswer((Answer<Integer>) (InvocationOnMock invocation) -> {
            saved[0]++;
            return saved[0];
        }).when(crudDao)
          .save(any(Customer.class));
        
        crudService.save(customer);
        assertEquals("1st not saved!", "Customer saved!", message[0]);
        
        crudService.save(customer);
        assertEquals("2nd saved!", 1, saved[0]);
        
        crudService.save(customer);
        assertEquals("2nd not saved!", 2, saved[0]);
        
        verify(crudDao, times(4)).save(customer);        
        verifyNoMoreInteractions(crudDao);
    }

    /**
     * Test of get method, of class CrudServiceImpl.
     */
    @Test
    public void testGet() {
        Customer customer = new CustomerBuilder()
                .setCustomerId(100L)
                .setName("Megan")
                .build();
        
        when(crudDao.get(100L)).thenReturn(customer);
        
        Customer getter = (Customer) crudService.get(100L);
        assertNotNull("Not a Customer!", getter);
        assertEquals("Customer Id not 100!", 100L, getter.getCustomerId(), 0.001);
        assertEquals("Customer entities are not the same!", getter, customer);
        
        verify(crudDao, atLeastOnce()).get(any(Long.class));
        verifyNoMoreInteractions(crudDao);
    }

    /**
     * Test of getAll method, of class CrudServiceImpl.
     */
    @Test
    public void testGetAll() {
        List<Customer> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            list.add(new CustomerBuilder()
                .setCustomerId(100L+i)
                .build());
        }
        
        assertTrue("List is empty!", list.size() > 0);
        
        when(crudDao.getAll()).thenReturn(list);
        
        List<Customer> customerList = crudService.getAll();
        assertNotNull("Customer list is null!", customerList);
        assertEquals("Customer lists are not the same!", list, customerList);
    }

    /**
     * Test of update method, of class CrudServiceImpl.
     */
    @Test
    public void testUpdate() {
        Customer customer = new Customer();
        
        when(crudDao.update(customer)).thenReturn(customer);
        
        Customer updated = (Customer) crudService.update(customer);
        assertEquals("Not updated!", customer, updated);
        
        verify(crudDao).update(any(Customer.class));
        verifyNoMoreInteractions(crudDao);
    }

    /**
     * Test of remove method, of class CrudServiceImpl.
     */
    @Test
    public void testRemove() {
        Customer customer = new Customer();
        final String[] message = new String[]{"", " removed!"};
        final int[] removed = new int[1];
        
        doNothing().when(crudDao).remove(customer);
        crudService.remove(customer);
        
        assertEquals("Something wrong!", 0, removed[0]);
        
        doAnswer((Answer<String>) new Answer() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                message[0] = customer.getClass().getSimpleName() + message[1];
                removed[0]++;
                return message[0];
            }
        }).doNothing()
        .doAnswer((Answer<Integer>) (InvocationOnMock invocation) -> {
            removed[0]++;
            return removed[0];
        }).when(crudDao)
          .remove(any(Customer.class));
        
        crudService.remove(customer);
        assertEquals("1st not removed!", "Customer removed!", message[0]);
        
        crudService.remove(customer);
        assertEquals("2nd removed!", 1, removed[0]);
        
        crudService.remove(customer);
        assertEquals("2nd not removed!", 2, removed[0]);
        
        verify(crudDao, times(4)).remove(customer);        
        verifyNoMoreInteractions(crudDao);
    }

    /**
     * Test of removeAll method, of class CrudServiceImpl.
     */
    @Test//(expected = NullPointerException.class)
    public void testRemoveAll() {
        final String s = "Removed all!";
        final String[] message = new String[1];
        final int[] removedAll = new int[1];
        
        doNothing().when(crudDao).removeAll();
        crudService.removeAll();
        
        assertEquals("Something wrong!", 0, removedAll[0]);
        
        doAnswer((Answer<String>) new Answer() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                message[0] = s;
                removedAll[0]++;
                return message[0];
            }
        }).doNothing()
        .doThrow(NullPointerException.class)
                .when(crudDao)
                .removeAll();
        
        crudService.removeAll();
        assertEquals("Not removed!", "Removed all!", message[0]);
        assertEquals("Not removed!", 1, removedAll[0]);
        
        crudService.removeAll();
        assertEquals("2nd removed!", 1, removedAll[0]);    
        
        //crudService.removeAll();
        
        try {
            crudService.removeAll();
            fail("Do not throw exception!");
        } catch (NullPointerException e) {
            // OK
        }
        
        verify(crudDao, times(4)).removeAll();        
        verifyNoMoreInteractions(crudDao);
    }

    /**
     * Test of isExist method, of class CrudServiceImpl.
     */
    @Test
    public void testIsExist() {
        when(crudDao.isExist(10L)).thenReturn(Boolean.FALSE);
        when(crudDao.isExist(100L)).thenReturn(Boolean.TRUE);
        
        assertTrue("Not exist!", crudService.isExist(100L));
        assertFalse("Exist!", crudService.isExist(10L));
        
        verify(crudDao, times(2)).isExist(any(Number.class));
        verifyNoMoreInteractions(crudDao);
    }

    /**
     * Test of isExistEntity method, of class CrudServiceImpl.
     */
    @Test
    public void testIsExistEntity() {
        Customer customer = new Customer();
        
        when(crudDao.isExistEntity(customer)).thenReturn(Boolean.TRUE);
        
        assertTrue("Not exist!", crudService.isExistEntity(customer));
        
        verify(crudDao, atLeastOnce()).isExistEntity(any(Customer.class));
        verifyNoMoreInteractions(crudDao);
    }

    /**
     * Test of clear method, of class CrudServiceImpl.
     */
    @Test
    public void testClear() {
        final String pUnit = "Persistence unit is ";
        final String clear = "clear!";
        final String[] message = new String[1];
        
        doNothing().when(crudDao).clear();
        crudService.clear();
        
        assertNull("Something wrong!", message[0]);
        
        doAnswer((Answer<String>) new Answer() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                message[0] = pUnit + clear;
                return message[0];
            }
        }).when(crudDao)
          .clear();
        
        crudService.clear();
        String result = "Persistence unit is clear!";
        assertEquals("", result, message[0]);
        
        verify(crudDao, times(2)).clear();
        verifyNoMoreInteractions(crudDao);
    }

    /**
     * Test of close method, of class CrudServiceImpl.
     */
    @Test
    public void testClose() {
        final String pUnit = "Persistence unit is ";
        final String close = "close!";
        final String[] message = new String[1];
        
        doNothing().when(crudDao).close();
        crudService.close();
        
        assertNull("Something wrong!", message[0]);
        
        doAnswer((Answer<String>) new Answer() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                message[0] = pUnit + close;
                return message[0];
            }
        }).when(crudDao)
          .close();
        
        crudService.close();
        String result = "Persistence unit is close!";
        assertEquals("", result, message[0]);
        
        verify(crudDao, times(2)).close();
        verifyNoMoreInteractions(crudDao);
    }

}