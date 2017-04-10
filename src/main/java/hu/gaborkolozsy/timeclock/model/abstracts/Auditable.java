/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.model.abstracts;

import hu.gaborkolozsy.timeclock.model.embedded.Audit;
import hu.gaborkolozsy.timeclock.model.embedded.AuditListener;

/**
 * Order some methode for {@code TimeClock} entities.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 * @see Audit
 * @see AuditListener
 */
public interface Auditable {

    /**
     * Return an {@code Audit} instance.
     * @return {@code Audit}
     * @see AuditListener
     */
    Audit getAudit();
}
