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
import org.springframework.test.context.junit4.SpringRunner;

/**
 * The persistence context test.
 * 
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 * @see EntityManager
 * @see org.junit.Assert
 * @see Test
 * @see Autowired
 */
@Development
@RunWith(SpringRunner.class)
public class PersistenceUnitTest {

    @Autowired
    private EntityManager entityManager;
    
    @Test
    public void testPersistenceUnit() {
        assertNotNull("The Persistence Unit is null!", entityManager);
    }
    
    @Test
    public void testPersistenceUnitIsOpen() {
        assertTrue("The Persistence Unit is close!", entityManager.isOpen());
    }
    
}