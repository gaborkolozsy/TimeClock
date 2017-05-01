/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.service.impl;

import hu.gaborkolozsy.timeclock.DevelopmentTest;
import hu.gaborkolozsy.timeclock.model.Developer;
import hu.gaborkolozsy.timeclock.service.DeveloperService;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;


/**
 * Test {@code DeveloperServiceImpl} class and part of {@code Developer} data 
 * access layer through with it.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 */
public class DeveloperServiceImplTest extends DevelopmentTest {

    @Autowired
    private DeveloperService developerService;
    
    /**
     * Test services that not null.
     */
    @Test
    public void testServicesNotNull() {
        assertNotNull(developerService);
    }

    /**
     * Test of getByDeveloperId method, of class DeveloperServiceImpl.
     */
    @Test
    public void testGetByDeveloperId() {
        Developer developer = developerService.getByDeveloperId(valid);        
        assertNotNull(isNull, developer);
        message = "Identifiers not equals!";
        assertEquals(message, valid, developer.getDeveloperId());
    }

    /**
     * Test of getAllByForename method, of class DeveloperServiceImpl.
     */
    @Test
    public void testGetAllByForename() {
        String forename = "Megan";
        List<Developer> developers = developerService.getAllByForename(forename);        
        message = "List size is not 2!";
        assertEquals(message, 2, developers.size());
        
        message = "Forename is not " + forename + "!";
        developers.forEach((developer) -> {
            assertEquals(message, forename, developer.getForename());
        });
    }

    /**
     * Test of updateLastnameByDeveloperId method, of class DeveloperServiceImpl.
     */
    @Test
    public void testUpdateLastnameByDeveloperId() {
        Developer developer = developerService.getByDeveloperId(valid);
        assertNotNull(isNull, developer);
        String lastName = "Fox";
        message = "Last name is not " + lastName + "!";
        assertEquals(message, lastName, developer.getLastName());        
        
        lastName = "Updated";
        developerService.updateLastnameByDeveloperId(valid, lastName);        
        developer = developerService.getByDeveloperId(valid);
        assertNotNull(isNull, developer);
        assertEquals(message, lastName, developer.getLastName());
    }

    /**
     * Test of removeByDeveloperId method, of class DeveloperServiceImpl.
     */
    @Test
    public void testRemoveByDeveloperId() {
        Developer developer = developerService.getByDeveloperId(valid);
        assertNotNull(isNull, developer);        
        
        developerService.removeByDeveloperId(valid);        
        result = developerService.isExistEntity(developer);
        message = "Developer exist!";
        assertFalse(message, result);
    }

    /**
     * Test of isExistWithDeveloperId method, of class DeveloperServiceImpl.
     */
    @Test
    public void testIsExistWithDeveloperId() {
        Developer developer = developerService.getByDeveloperId(valid);
        assertNotNull(isNull, developer);        
        
        result = developerService.isExistWithDeveloperId(developer.getDeveloperId());
        message = "Developer not exist!";
        assertTrue(message, result);        
        
        result = developerService.isExistWithDeveloperId(invalid);
        message = "Developer exist!";
        assertFalse(message, result);
    }
    
    /**
     * Test throws exception by non exist ID.
     */
    @Test
    public void testThrowsExceptionWhenIdIsNotExist() {
        message = "Throws exception test is not ok!";
        assertEquals(message, "OK", 
                new ExceptionVerifier(() -> developerService.getByDeveloperId(invalid))
                .isThrowing(EmptyResultDataAccessException.class));
    }
    
}