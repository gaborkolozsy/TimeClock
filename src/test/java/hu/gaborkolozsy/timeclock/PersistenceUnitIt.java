/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Timed;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * The persistence context test.
 * 
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 * @see EntityManager
 * @see org.junit.Assert
 * @see Test
 * @see RunWith
 * @see Autowired
 * @see SpringJUnit4ClassRunner
 */
@Development
@RunWith(SpringJUnit4ClassRunner.class)
public class PersistenceUnitIt {

    @Autowired
    private EntityManager entityManager;
    
    @Test
    @Timed(millis = 100L)
    public void testPersistenceUnit() {
        assertNotNull("The Persistence Unit is null!", entityManager);
    }
    
    @Test
    @Timed(millis = 100L)
    public void testPersistenceUnitIsOpen() {
        assertTrue("The Persistence Unit is close! Expected -> Open", entityManager.isOpen());
    }
    
}