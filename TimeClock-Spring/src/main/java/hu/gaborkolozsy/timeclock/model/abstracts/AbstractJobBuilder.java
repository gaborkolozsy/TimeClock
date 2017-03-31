/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.model.abstracts;

import hu.gaborkolozsy.timeclock.model.Job;
import hu.gaborkolozsy.timeclock.model.Job.JobBuilder;

/**
 * Order some abstract methode for {@link Job} entity's {@link JobBuilder} 
 * class.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @param <E> type of entity
 * @param <B> type of builder 
 * @since 0.0.1-SNAPSHOT
 */
public abstract class AbstractJobBuilder<E, B> extends Entity<E, B> implements Builder<E> {

    /**
     * Set the project' name.
     * @param projectName the project's name
     * @return builder implement class
     */
    public abstract B setProjectName(String projectName);
    
    /**
     * Set the developer's branch (e.g. GitHub).
     * @param branchName the developer's branch
     * @return builder implement class
     */
    public abstract B setBranchName(String branchName);   
        
    /**
     * Set the package's name.
     * @param packageName the package's name
     * @return builder implement class
     */
    public abstract B setPackageName(String packageName);
    
    /**
     * Set the class' name.
     * @param className the class' name
     * @return builder implement class
     */
    public abstract B setClassName(String className);
    
    /**
     * Set the status.
     * @param status the status
     * @return builder implement class
     */
    public abstract B setStatus(String status);
    
    /**
     * Set the comment.
     * @param comment the comment
     * @return builder implement class
     */
    public abstract B setComment(String comment);
    
}
