/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.service.impl;

import hu.gaborkolozsy.timeclock.DevelopmentTest;
import hu.gaborkolozsy.timeclock.model.Developer;
import hu.gaborkolozsy.timeclock.service.DeveloperService;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
    
    private final long ID = 100L;
    
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
        Developer developer = developerService.getByDeveloperId(ID);
        
        assertNotNull("Developer is null!", developer);
        long id = developer.getDeveloperId();
        assertEquals("Identifiers not equals!", ID, id);
    }

    /**
     * Test of getAllByForename method, of class DeveloperServiceImpl.
     */
    @Test
    public void testGetAllByForename() {
        List<Developer> developers = developerService.getAllByForename("Megan");
        
        assertEquals("Size of developer list is not 2!", 2, developers.size());
        developers.forEach((dev) -> {
            assertEquals("Forename is not Megan!", "Megan", dev.getForename());
        });
    }

    /**
     * Test of updateLastnameByDeveloperId method, of class DeveloperServiceImpl.
     */
    @Test
    public void testUpdateLastnameByDeveloperId() {
        Developer developer = developerService.getByDeveloperId(ID);
        assertNotNull("Developer is null!", developer);
        assertEquals("Fox", developer.getLastName());
        
        developerService.updateLastnameByDeveloperId(ID, "updated");
        
        developer = developerService.getByDeveloperId(ID);
        assertNotNull("Developer is null!", developer);
        assertEquals("Not updated!", "updated", developer.getLastName());
    }

    /**
     * Test of removeByDeveloperId method, of class DeveloperServiceImpl.
     */
    @Test
    public void testRemoveByDeveloperId() {
        Developer developer = developerService.getByDeveloperId(ID);
        assertNotNull("Developer null!", developer);
        
        developerService.removeByDeveloperId(ID);
        
        boolean result = developerService.isExistEntity(developer);
        assertEquals("Developer exist!", false, result);
    }

    /**
     * Test of isExistByDeveloperId method, of class DeveloperServiceImpl.
     */
    @Test
    public void testIsExistByDeveloperId() {
        Developer developer = developerService.getByDeveloperId(ID);
        assertNotNull("Developer null!", developer);
        
        boolean result = developerService.isExistByDeveloperId(developer.getDeveloperId());
        assertEquals("Developer not exist!", true, result);
        
        result = developerService.isExistByDeveloperId(1L);
        assertEquals("Developer exist!", false, result);
    }
    
    /**
     * Test throws exception by non exist ID.
     */
    @Test
    public void testThrowsException_WhenIdIsNotExist() {
        new ExceptionVerifier(() -> developerService.getByDeveloperId(1L))
                .isThrowing(EmptyResultDataAccessException.class);
    }
    
}