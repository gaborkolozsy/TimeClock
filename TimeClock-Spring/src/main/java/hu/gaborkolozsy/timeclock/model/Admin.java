/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.model;

/**
 * The actual system user.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 * @see Audit
 * @see Auditable
 * @see AuditListener
 */
public class Admin {

    /**
     * Call from {@link AuditListener}.
     * @return the actual system user's name
     */
    public static String getAdmin() {
        return System.getProperty("user.name");
    }
    
}
