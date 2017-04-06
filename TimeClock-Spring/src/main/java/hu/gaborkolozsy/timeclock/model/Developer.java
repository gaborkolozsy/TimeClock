/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.model;

import hu.gaborkolozsy.timeclock.model.abstracts.AbstractDeveloperBuilder;
import hu.gaborkolozsy.timeclock.model.abstracts.Auditable;
import hu.gaborkolozsy.timeclock.model.abstracts.Builder;
import hu.gaborkolozsy.timeclock.model.embedded.Address;
import hu.gaborkolozsy.timeclock.model.embedded.Audit;
import hu.gaborkolozsy.timeclock.model.embedded.AuditListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.loader.MultipleBagFetchException;

/**
 * Represent a developer. This will be stored in database as a table 
 * and it will be called "DEVELOPER".
 * 
 * <p>The {@link Audit} is embedded.
 * 
 * <p><strong>
 * If want {@code Developer_Id} column for referenced column by @ManyToOne 
 * relationship instead of default primary key, than {@code Developer} entity 
 * must implements the {@code Serializable} interface.
 * 
 * <p>
 * Without @Fetch(FetchMode.SELECT) hibernate throws a 
 * {@link MultipleBagFetchException}. Cause: cannot simultaneously fetch 
 * multiple bags. With {@code List} don't, but with {@code Set} work good.
 * </strong>
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 * @see AbstractDeveloperBuilder
 * @see Builder
 * @see Address
 * @see Audit
 * @see AuditListener
 * @see ArrayList
 * @see List
 * @see CascadeType
 * @see Column
 * @see Embedded
 * @see Entity
 * @see EntityListeners
 * @see FetchType
 * @see GeneratedValue
 * @see GenerationType
 * @see Id
 * @see NamedQueries
 * @see NamedQuery
 * @see OneToMany
 * @see SequenceGenerator
 * @see Version
 * @see DynamicInsert
 */
@Entity(name = "Developer")
@EntityListeners(AuditListener.class)
@DynamicInsert
@NamedQueries({ 
    @NamedQuery(name = "getByDeveloperId", 
                query = "from Developer d where d.developerId = :developerId"),
    @NamedQuery(name = "getAllByForename", 
                query = "from Developer d where d.forename = :forename")
})
@SuppressWarnings({"PersistenceUnitPresent"})
public class Developer implements Auditable, Serializable {

    @Id
    @GeneratedValue(generator = "developerGEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(initialValue = 100, name = "developerGEN", sequenceName = "developerSEQ")
    @Column(name = "Id", nullable = false, unique = true, updatable = false)
    private Long id;
    
    @Column(name = "Developer_Id", nullable = false, unique = true, updatable = false)
    private int developerId;
    
    @Column(name = "Forename", nullable = false)
    private String forename;
    
    @Column(name = "Last_Name")
    private String lastName;
    
    @Fetch(FetchMode.SELECT)
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy = "developer", targetEntity = Job.class)
    private List<Job> jobs = new ArrayList<>(0);
    
    @Fetch(FetchMode.SELECT)
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy = "developer", targetEntity = WorkingHours.class)
    private List<WorkingHours> workingHours = new ArrayList<>(0);
    
    @Embedded
    private Address address;
    
    @Embedded
    private final Audit audit = new Audit();
    
    @Version
    @Column(name = "Version", nullable = false)
    private int version;

    /**
     * Returns developer's generated ID.
     * @return ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns developer's natural ID.
     * @return developer's natural ID
     */
    public int getDeveloperId() {
        return developerId;
    }

    /**
     * Returns developer's forename.
     * @return developer's forename
     */
    public String getForename() {
        return forename;
    }

    /**
     * Returns developer's last name.
     * @return developer's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns developer's jobs as a list.
     * @return developer's jobs
     */
    public List<Job> getJobs() {
        return jobs;
    }

    /**
     * Returns developer's working hours as a list.
     * @return developer's working hours
     */
    public List<WorkingHours> getWorkingHours() {
        return workingHours;
    }

    /**
     * Returns developer's address.
     * @return developer's address
     */
    public Address getAddress() {
        return address;
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
     * Returns the {@link Developer}'s entity actual version.
     * @return version No.
     */
    public int getVersion() {
        return version;
    }
    
    /**
     * {@code DeveloperBuilder} is used to build instances of 
     * {@link Developer} from values configured by the setters.
     * 
     * <p>The class is achieves the Build design pattern.
     *
     * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
     * @since 0.0.1-SNAPSHOT
     * @see Builder
     * @see AbstractDeveloperBuilder
     */
    public static class DeveloperBuilder extends AbstractDeveloperBuilder<Developer, DeveloperBuilder> {

        /**
         * Constructor without parameter.
         */
        public DeveloperBuilder() {
            super.entity = new Developer();
        }

        /**
         * Constructor with one parameter.
         * @param developer {@code Developer}
         */
        public DeveloperBuilder(Developer developer) {
            super.entity = developer;
        }

        /**
         * Create a new {@code DeveloperBuilder} instance.
         * @return a new {@code DeveloperBuilder} instance
         */
        public static DeveloperBuilder create() {
            return new DeveloperBuilder();
        }

        /**
         * Set the developer's natural ID.
         * @param developerId developer's natural ID
         * @return this
         */
        @Override
        public DeveloperBuilder setDeveloperId(int developerId) {
            super.entity.developerId = developerId;
            return this;
        }

        /**
         * Set the developer's forename.
         * @param forename developer's forename
         * @return this
         */
        @Override
        public DeveloperBuilder setForename(String forename) {
            super.entity.forename = forename;
            return this;
        }

        /**
         * Set the developer's last name.
         * @param lastName developer's last name
         * @return this
         */
        @Override
        public DeveloperBuilder setLastName(String lastName) {
            super.entity.lastName = lastName;
            return this;
        }

        /**
         * Set the {@link Developer}'s job list.
         * @param jobs {@link Developer}'s jobs
         * @return builder implement class
         */
        @Override
        public DeveloperBuilder setJobs(List<Job> jobs) {
            super.entity.jobs = jobs;
            return this;
        }
        
        /**
         * Set the {@link Developer}'s working hours list.
         * @param workingHours {@link Developer}'s working hours
         * @return this
         */
        @Override
        public DeveloperBuilder setWorkingHours(List<WorkingHours> workingHours) {
            super.entity.workingHours = workingHours;
            return this;
        }
        
        /**
         * Set the developer's {@code Address}.
         * @param address developer's {@code Address}
         * @return this
         */
        @Override
        public DeveloperBuilder setAddress(Address address) {
            super.entity.address = address;
            return this;
        }

        /**
         * Return a new {@code Developer} set instance.
         * @return {@code Developer}
         */
        @Override
        public Developer build() {
            return super.entity;
        }

    }
    
}

