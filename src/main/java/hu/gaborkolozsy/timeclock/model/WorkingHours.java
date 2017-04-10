/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.model;

import hu.gaborkolozsy.timeclock.model.abstracts.AbstractWorkingHoursBuilder;
import hu.gaborkolozsy.timeclock.model.abstracts.Auditable;
import hu.gaborkolozsy.timeclock.model.embedded.Audit;
import hu.gaborkolozsy.timeclock.model.embedded.AuditListener;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

/**
 * Represent an working hours. This will be stored in database as a table 
 * and it will be called "WORKING_HOURS".
 * 
 * <p>The {@link Audit} is embedded.
 * 
 * <p>@DynamicUpdate and @SelectBeforeUpdate annotations not working 
 * with @PreUpdate ({@code Audit} class) annotation. But update is dynamic now.
 * 
 * <p><strong>
 * If want {@code Developer_Id} column for referenced column by @ManyToOne 
 * relationship instead of default primary key, than {@code Developer} entity 
 * must implements the {@code Serializable} interface.</strong>
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 * @see AbstractWorkingHoursBuilder
 * @see Audit
 * @see AuditListener
 * @see Serializable
 * @see LocalDate
 * @see LocalDateTime
 * @see Column
 * @see Embedded
 * @see Entity
 * @see EntityListeners
 * @see GeneratedValue
 * @see GenerationType
 * @see Id
 * @see JoinColumn
 * @see ManyToOne
 * @see SequenceGenerator
 * @see Version
 * @see DynamicInsert
 * @see DynamicUpdate
 * @see SelectBeforeUpdate
 */
@Entity(name = "Working_Hours")
@EntityListeners(AuditListener.class)
@DynamicInsert
@SuppressWarnings({"PersistenceUnitPresent", "SerializableClass"})
public class WorkingHours implements Auditable {

    @Id
    @GeneratedValue(generator = "workingHoursGEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(allocationSize = 1, name = "workingHoursGEN", sequenceName = "workingHoursSEQ")
    @Column(name = "Id", nullable = false, unique = true, updatable = false)
    private Long id;
    
    @Column(name = "Work_Day", updatable = false, nullable = false)
    private LocalDate day;
    
    @Column(name = "Work_Begin", updatable = false, nullable = false)
    private LocalDateTime workStart;
    
    @Column(name = "Work_End")
    private LocalDateTime workEnd;
    
    @ManyToOne
    @JoinColumn(name = "Developer_Id", nullable = false, referencedColumnName = "Developer_Id")
    private Developer developer;
    
    @Embedded
    private final Audit audit = new Audit();
    
    @Version
    @Column(name = "Version", nullable = false)
    private int version;

    /**
     * Returns working hours' generated ID.
     * @return working hours' ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns working hours' day.
     * @return day
     */
    public LocalDate getDay() {
        return day;
    }

    /**
     * Returns working hours' start time.
     * @return working hours' start time
     */
    public LocalDateTime getWorkStart() {
        return workStart;
    }

    /**
     * Returns working hours' end time.
     * @return working hours' end time
     */
    public LocalDateTime getWorkEnd() {
        return workEnd;
    }
    
    /**
     * Returns working hours' developer.
     * @return developer's ID
     */
    public Developer getDeveloper() {
        return developer;
    }

    /**
     * Returns the {@code Audit} object.
     * @return {@code Audit}
     */
    @Override
    public Audit getAudit() {
        return audit;
    }
    
    /**
     * Returns the {@link WorkingHours}' entity actual version.
     * @return version No.
     */
    public int getVersion() {
        return version;
    }
    
    /**
     * {@code WorkingHoursBuilder} is used to build instances of 
     * {@code WorkingHours} from values configured by the setters. 
     * 
     * <p>The class is achieves the Build design pattern.
     *
     * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
     * @since 0.0.1-SNAPSHOT
     * @see Builder
     * @see AbstractWorkingHoursBuilder
     * @see LocalDate
     * @see LocalDateTime
     */
    public static class WorkingHoursBuilder extends AbstractWorkingHoursBuilder<WorkingHours, WorkingHoursBuilder> {

        /**
         * Constructor without parameter.
         */
        public WorkingHoursBuilder() {
            super.entity = new WorkingHours();
        }

        /**
         * Constructor with parameter.
         * @param workingHours {@code WorkingHours}
         */
        public WorkingHoursBuilder(WorkingHours workingHours) {
            super.entity = workingHours;
        }

        /**
         * Create a new {@code WorkingHoursBuilder} instance.
         * @return a new {@code WorkingHoursBuilder} instance
         */
        public static WorkingHoursBuilder create() {
            return new WorkingHoursBuilder();
        }
        
        /**
         * Set the actual work day.
         * @param day actual work day
         * @return this
         */
        @Override
        public WorkingHoursBuilder setDay(LocalDate day) {
            super.entity.day = day;
            return this;
        }

        /**
         * Set the start of working hours.
         * @param start start of working hours
         * @return this
         */
        @Override
        public WorkingHoursBuilder setWorkStart(LocalDateTime start) {
            super.entity.workStart = start;
            return this;
        } 

        /**
         * Set the end of working hours.
         * @param workEnd end of working hours
         * @return this
         */
        @Override
        public WorkingHoursBuilder setWorkEnd(LocalDateTime workEnd) {
            super.entity.workEnd = workEnd;
            return this;
        }
        
        /**
         * Set the developer.
         * @param developer developer
         * @return this
         */
        @Override
        public WorkingHoursBuilder setDeveloper(Developer developer) {
            super.entity.developer = developer;
            return this;
        }

        /**
         * Return a new {@code WorkingHours} set instance.
         * @return {@code WorkingHours}
         */
        @Override
        public WorkingHours build() {
            return super.entity;
        }

    }
    
}
