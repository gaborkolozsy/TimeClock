/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.service.impl;

import hu.gaborkolozsy.timeclock.DevelopmentTest;
import hu.gaborkolozsy.timeclock.model.Pay;
import hu.gaborkolozsy.timeclock.service.PayService;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

/**
 * Test {@code PayServiceImpl} class and part of {@code Pay} data access layer 
 * through with it.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 */
public class PayServiceImplTest extends DevelopmentTest {

    @Autowired
    private PayService payService;
    
    /**
     * Test of getByPayId method, of class PayServiceImpl.
     */
    @Test
    public void testGetByPayId() {
        Pay pay = payService.getByPayId(PAYID+VALID);        
        assertNotNull(ISNULL, pay);
        message = "Identifiers not equals!";
        assertEquals(message, PAYID+VALID, pay.getPayId());
    }

    /**
     * Test of getByPayable method, of class PayServiceImpl.
     */
    @Test
    public void testGetByPayable() {
        List<Pay> pays = payService.getByPayable(true);
        message = "List size is not 2!";
        assertEquals(message, 2, pays.size());
        
        message = "Payable is false!";
        pays.forEach((pay) -> {
            assertTrue(message, pay.isPayable());
        });
    }

    /**
     * Test of getByPaid method, of class PayServiceImpl.
     */
    @Test
    public void testGetByPaid() {
        List<Pay> pays = payService.getByPaid(false);
        message = "List size is not 2!";
        assertEquals(message, 2, pays.size());
        
        message = "Paid is false!";
        pays.forEach((pay) -> {
            assertFalse(message, pay.isPaid());
        });
    }

    /**
     * Test of updatePaymentByPayId method, of class PayServiceImpl.
     */
    @Test
    public void testUpdatePaymentByPayId() {
        Pay pay = payService.getByPayId(PAYID+VALID);        
        assertNotNull(ISNULL, pay);
        double payment = 0.0;
        assertEquals(message, payment, pay.getPayment(), 0.001);
        
        payment = 531.72;
        payService.updatePaymentByPayId(PAYID+VALID, payment);
        pay = payService.getByPayId(PAYID+VALID);
        assertNotNull(ISNULL, pay);
        message = "Payment is null!";
        assertNotNull(message, pay.getPayment());
        message = "Payment is not " + payment + "!";
        assertEquals(message, payment, pay.getPayment(), 0.001);
    }

    /**
     * Test of updatePayableByPayId method, of class PayServiceImpl.
     */
    @Test
    public void testUpdatePayableByPayId() {
        Pay pay = payService.getByPayId(PAYID+VALID);        
        assertNotNull(ISNULL, pay);
        message = "Payable is not true!";
        assertTrue(message, pay.isPayable());
        
        payService.updatePayableByPayId(PAYID+VALID, false);
        pay = payService.getByPayId(PAYID+VALID);
        assertNotNull(ISNULL, pay);
        message = "Payable is not false!";
        assertFalse(message, pay.isPayable());
    }

    /**
     * Test of updatePaidByPayId method, of class PayServiceImpl.
     */
    @Test
    public void testUpdatePaidByPayId() {
        Pay pay = payService.getByPayId(PAYID+VALID);        
        assertNotNull(ISNULL, pay);
        message = "Paid is not false!";
        assertFalse(message, pay.isPaid());
        
        payService.updatePaidByPayId(PAYID+VALID, true);
        pay = payService.getByPayId(PAYID+VALID);
        assertNotNull(ISNULL, pay);
        message = "Paid is not true!";
        assertTrue(message, pay.isPaid());
    }

    /**
     * Test of removeByPayId method, of class PayServiceImpl.
     */
    @Test
    public void testRemoveByPayId() {
        Pay pay = payService.getByPayId(PAYID+VALID);        
        assertNotNull(ISNULL, pay);
        
        payService.removeByPayId(PAYID+VALID);
        result = payService.isExistEntity(pay);
        message = "Pay is exist!";
        assertFalse(message, result);
    }

    /**
     * Test of isExistWithPayId method, of class PayServiceImpl.
     */
    @Test
    public void testIsExistWithPayId() {
        Pay pay = payService.getByPayId(PAYID+VALID);        
        assertNotNull(ISNULL, pay);
        
        result = payService.isExistWithPayId(PAYID+VALID);
        message = "Pay is not exist!";
        assertTrue(message, result);
        
        result = payService.isExistWithPayId(PAYID+INVALID);
        message = "Pay is exist!";
        assertFalse(message, result);
    }

    /**
     * Test of isPayable method, of class PayServiceImpl.
     */
    @Test
    public void testIsPayable() {
        Pay pay = payService.getByPayId(PAYID+VALID);        
        assertNotNull(ISNULL, pay);
        message = "Pay is not payable!";
        assertTrue(message, pay.isPayable());
        
        result = payService.isPayable(PAYID+VALID);
        assertTrue(message, result);
        
        payService.updatePayableByPayId(PAYID+VALID, false);
        result = payService.isPayable(PAYID+VALID);
        message = "Pay is still payable!";
        assertFalse(message, result);
    }

    /**
     * Test of isPaid method, of class PayServiceImpl.
     */
    @Test
    public void testIsPaid() {
        Pay pay = payService.getByPayId(PAYID+VALID);        
        assertNotNull(ISNULL, pay);
        message = "Pay is not paid!";
        assertFalse(message, pay.isPaid());
        
        result = payService.isPaid(PAYID+VALID);
        assertFalse(message, result);
        
        payService.updatePaidByPayId(PAYID+VALID, true);
        result = payService.isPaid(PAYID+VALID);
        message = "Pay is not paid!";
        assertTrue(message, result);
    }
    
    /**
     * Test throws exception by non exist ID.
     */
    @Test
    public void testThrowsExceptionWhenIdIsNotExist() {
        message = "Throws exception test is not ok!";
        assertEquals(message, "OK", 
                new ExceptionVerifier(() -> payService.getByPayId(PAYID+INVALID))
                .isThrowing(EmptyResultDataAccessException.class));
    }

}