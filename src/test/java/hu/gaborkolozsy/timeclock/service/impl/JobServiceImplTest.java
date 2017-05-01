/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.service.impl;

import hu.gaborkolozsy.timeclock.DevelopmentTest;
import hu.gaborkolozsy.timeclock.model.Job;
import hu.gaborkolozsy.timeclock.service.JobService;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

/**
 * Test {@code JobServiceImpl} class and part of {@code Job} data access layer 
 * through with it.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 */
public class JobServiceImplTest extends DevelopmentTest {

    @Autowired
    private JobService jobService;
    
    /**
     * Test of getByOrderNumber method, of class JobServiceImpl.
     */
    @Test
    public void testGetByOrderNumber() {
        Job job = jobService.getByOrderNumber(VALID);
        assertNotNull(ISNULL, job);
        message = "Identifiers not equals!";
        assertEquals(message, VALID, job.getOrderNumber());
    }

    /**
     * Test of getAllByProjectName method, of class JobServiceImpl.
     */
    @Test
    public void testGetAllByProjectName() {
        String name = "Project";
        List<Job> jobs = jobService.getAllByProjectName(name);        
        message = "List size is not 2!";
        assertEquals(message, 2, jobs.size());
        
        message = "Project name is not " + name + "!";
        jobs.forEach((job) -> {
            assertEquals(message, name, job.getProjectName());
        });
    }

    /**
     * Test of getAllByStatus method, of class JobServiceImpl.
     */
    @Test
    public void testGetAllByStatus() {
        String status = "WIP";
        List<Job> jobs = jobService.getAllByStatus(status);        
        message = "List size is not 2!";
        assertEquals(message, 2, jobs.size());
        
        message = "Status is not " + status + "!";
        jobs.forEach((job) -> {
            assertEquals(message, status, job.getStatus());
        });
    }

    /**
     * Test of updateStatusByOrderNumber method, of class JobServiceImpl.
     */
    @Test
    public void testUpdateStatusByOrderNumber() {
        Job job = jobService.getByOrderNumber(VALID);
        assertNotNull(ISNULL, job);
        String status = "WIP";
        message = "Status is not " + status + "!";        
        assertEquals(message, status, job.getStatus());
        
        status = "DONE";
        jobService.updateStatusByOrderNumber(VALID, status);
        job = jobService.getByOrderNumber(VALID);
        assertNotNull(ISNULL, job);
        assertEquals(message, status, job.getStatus());
    }

    /**
     * Test of updateCommentByOrderNumber method, of class JobServiceImpl.
     */
    @Test
    public void testUpdateCommentByOrderNumber() {
        Job job = jobService.getByOrderNumber(VALID);
        assertNotNull(ISNULL, job);
        message = "Comment is not null!";
        assertNull(message, job.getComment());
        
        String comment = "blah bla";
        jobService.updateCommentByOrderNumber(VALID, comment);
        job = jobService.getByOrderNumber(VALID);
        assertNotNull(ISNULL, job);
        message = "Comment is not " + comment + "!";
        assertEquals(message, comment, job.getComment());
    }

    /**
     * Test of removeByOrderNumber method, of class JobServiceImpl.
     */
    @Test
    public void testRemoveByOrderNumber() {
        Job job = jobService.getByOrderNumber(VALID);
        assertNotNull(ISNULL, job);
        
        jobService.removeByOrderNumber(VALID);
        result = jobService.isExistEntity(job);
        message = "Job is exist!";
        assertFalse(message, result);
    }

    /**
     * Test of isExistWithOrderNumber method, of class JobServiceImpl.
     */
    @Test
    public void testIsExistWithOrderNumber() {
        Job job = jobService.getByOrderNumber(VALID);
        assertNotNull(ISNULL, job);
        
        result = jobService.isExistWithOrderNumber(VALID);
        message = "Job is not exist!";
        assertTrue(message, result);
        
        result = jobService.isExistWithOrderNumber(INVALID);
        message = "Job is exist!";
        assertFalse(message, result);
    }
    
    /**
     * Test throws exception by non exist ID.
     */
    @Test
    public void testThrowsExceptionWhenIdIsNotExist() {
        message = "Throws exception test is not ok!";
        assertEquals(message, "OK", 
                new ExceptionVerifier(() -> jobService.getByOrderNumber(INVALID))
                .isThrowing(EmptyResultDataAccessException.class));
    }

}