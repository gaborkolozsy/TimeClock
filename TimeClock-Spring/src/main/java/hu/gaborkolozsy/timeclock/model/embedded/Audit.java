/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.model.embedded;

import hu.gaborkolozsy.timeclock.model.abstracts.AbstractAuditBuilder;
import hu.gaborkolozsy.timeclock.model.abstracts.Auditable;
import hu.gaborkolozsy.timeclock.model.abstracts.Builder;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Defines an {@code Audit} class whose instances are stored as an intrinsic
 * part of an owning entity and share the identity of the entity.<br>
 * Each of the persistent properties or fields of the embedded
 * object is mapped to the database table for the entity.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 * @see AbstractAuditBuilder
 * @see Builder
 * @see LocalDateTime
 * @see Column
 * @see Embeddable
 * @see Auditable
 * @see AuditListener
 */
@Embeddable
@SuppressWarnings("SerializableClass")
public class Audit {

    @Column(name = "Created", updatable = false, nullable = false)
    protected LocalDateTime created;
    
    @Column(name = "Created_By", updatable = false, nullable = false)
    protected String createdBy;
    
    @Column(name = "Updated")
    protected LocalDateTime updated;
    
    @Column(name = "Updated_By")
    protected String updatedBy;

    /**
     * Returns the record when was created.
     * @return the created time
     */
    public LocalDateTime getCreated() {
        return created;
    }

    /**
     * Returns the record's creater(admin).
     * @return creater(admin's name)
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Returns the record when was updated.
     * @return the updated time
     */
    public LocalDateTime getUpdated() {
        return updated;
    }

    /**
     * Returns the record's updater(admin).
     * @return updater(admin's name)
     */
    public String getUpdatedBy() {
        return updatedBy;
    }
    
    /**
    * {@code AuditBuilder} is used to build instances of 
    * {@link Audit} from values configured by the setters.
    * 
    * <p>The class is achieves the Build design pattern.
    *
    * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
    * @since 0.0.1-SNAPSHOT
    * @see LocalDateTime
    * @see Builder
    * @see AbstractAuditBuilder
    */
    public static class AuditBuilder extends AbstractAuditBuilder<Audit, AuditBuilder> {

        /**
         * Constructor without parameter.
         */
        public AuditBuilder() {
            super.entity = new Audit();
        }

        /**
         * Constructor with one parameter.
         * @param audit {@code Audit}
         */
        public AuditBuilder(Audit audit) {
            super.entity = audit;
        }

        /**
         * Create a new {@code AuditBuilder} instance.
         * @return a new {@code AuditBuilder} instance
         */
        public static AuditBuilder create() {
            return new AuditBuilder();
        }

        /**
         * Set when created the {@code Audit} entity.
         * @param created created
         * @return this
         */
        @Override
        public AuditBuilder setCreated(LocalDateTime created) {
            super.entity.created = created;
            return this;
        }

        /**
         * Set who created the {@code Audit} entity.
         * @param createdBy created by
         * @return this
         */
        @Override
        public AuditBuilder setCreatedBy(String createdBy) {
            super.entity.createdBy = createdBy;
            return this;
        }

        /**
         * Set who updated the {@code Audit} entity.
         * @param updated updated
         * @return this
         */
        @Override
        public AuditBuilder setUpdated(LocalDateTime updated) {
            super.entity.updated = updated;
            return this;
        }

        /**
         * Set when updated the {@code Audit} entity.
         * @param updatedBy updated by
         * @return this
         */
        @Override
        public AuditBuilder setUpdatedBy(String updatedBy) {
            super.entity.updatedBy = updatedBy;
            return this;
        }

        /**
         * Return a new {@code Audit} set instance.
         * @return {@code Audit}
         */
        @Override
        public Audit build() {
            return entity;
        }

    }
    
}
