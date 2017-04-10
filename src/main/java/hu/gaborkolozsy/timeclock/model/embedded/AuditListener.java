/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.model.embedded;

import hu.gaborkolozsy.timeclock.model.Admin;
import hu.gaborkolozsy.timeclock.model.abstracts.Auditable;
import java.time.LocalDateTime;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * Set audit filds for entity.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 * @see Admin
 * @see Audit
 * @see Auditable
 * @see LocalDateTime
 * @see PrePersist
 * @see PreUpdate
 */
public class AuditListener {

    /**
     * Set creater by persist.
     * @param auditable {@link Auditable} interface
     */
    @PrePersist
    public void setCreated(Auditable auditable) {
        new Audit.AuditBuilder(auditable.getAudit()).setCreated(LocalDateTime.now());
        new Audit.AuditBuilder(auditable.getAudit()).setCreatedBy(Admin.getAdmin());
        //auditable.getAudit().created = LocalDateTime.now();
        //auditable.getAudit().createdBy = Admin.getAdmin();
    }
    
    /**
     * Set updater by merge.
     * @param auditable {@link Auditable} interface
     */
    @PreUpdate
    public void setUpdated(Auditable auditable) {
        new Audit.AuditBuilder(auditable.getAudit()).setUpdated(LocalDateTime.now());
        new Audit.AuditBuilder(auditable.getAudit()).setUpdatedBy(Admin.getAdmin());
        //auditable.getAudit().updated = LocalDateTime.now();
        //auditable.getAudit().updatedBy = Admin.getAdmin();
    }
}
