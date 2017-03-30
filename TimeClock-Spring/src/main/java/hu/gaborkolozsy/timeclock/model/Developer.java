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
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import org.hibernate.annotations.DynamicInsert;

/**
 * Represent a developer. This will be stored in database as a table 
 * and it will be called "DEVELOPER".
 * 
 * <p>The {@link Audit} is embedded.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 * @see AbstractDeveloperBuilder
 * @see Builder
 * @see Address 
 * @see AuditListener
 * @see Set
 * @see CascadeType
 * @see Column
 * @see Embedded
 * @see Entity
 * @see EntityListeners
 * @see FetchType
 * @see GeneratedValue
 * @see Id
 * @see OneToMany
 * @see Version
 * @see DynamicInsert
 */
@Entity(name = "Developer")
@EntityListeners(AuditListener.class)
@DynamicInsert                                                                  // I have to control this
@SuppressWarnings({"PersistenceUnitPresent", "SerializableClass"})
public class Developer implements Auditable {

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false, unique = true, updatable = false)
    private int id;
    
    @Column(name = "Developer_Id", nullable = false, unique = true, updatable = false)
    private int developerId;
    
    @Column(name = "Fore_Name", nullable = false)
    private String forename;
    
    @Column(name = "Last_Name", nullable = false)
    private String lastName;
    
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, /*mappedBy = "Developer",*/ targetEntity = Job.class)
    private Set<Job> job;
    
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, /*mappedBy = "Developer",*/ targetEntity = WorkingHours.class)
    private Set<WorkingHours> workingHours;
    
    @Embedded
    private final Address address = new Address();
    
    @Embedded
    private final Audit audit = new Audit();
    
    @Version
    @Column(name = "Version", nullable = false)
    private int version;

    /**
     * Returns the ID.
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the specified developer's ID.
     * @return developer's ID
     */
    public int getDeveloperId() {
        return developerId;
    }

    /**
     * Returns the specified developer's fore name.
     * @return developer's fore name
     */
    public String getForename() {
        return forename;
    }

    /**
     * Returns the specified developer's last name.
     * @return developer last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the specified developer's jobs as a set.
     * @return developer's jobs
     */
    public Set<Job> getJob() {
        return job;
    }

    /**
     * Returns the specified developer's working hours as a {@code Set}.
     * @return developer's working hours set
     */
    public Set<WorkingHours> getWorkingHours() {
        return workingHours;
    }

    /**
     * Returns the specified developer's address.
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
     * @return version
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
    public static class DeveloperBuilder extends 
            AbstractDeveloperBuilder<Developer, DeveloperBuilder> {

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
         * Return a new {@code Developer} set instance.
         * @return {@code Developer}
         */
        @Override
        public Developer build() {
            return super.entity;
        }

    }
    
}

