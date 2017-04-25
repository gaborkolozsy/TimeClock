/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.model;

import hu.gaborkolozsy.timeclock.model.abstracts.AbstractCustomerBuilder;
import hu.gaborkolozsy.timeclock.model.abstracts.Auditable;
import hu.gaborkolozsy.timeclock.model.abstracts.Builder;
import hu.gaborkolozsy.timeclock.model.embedded.Address;
import hu.gaborkolozsy.timeclock.model.embedded.Audit;
import hu.gaborkolozsy.timeclock.model.embedded.AuditListener;
import java.io.Serializable;
import java.time.LocalDateTime;
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
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;
import org.hibernate.annotations.DynamicInsert;

/**
 * Represent a customer. This will be stored in database and it will be 
 * called "CUSTOMER".
 * 
 * <p>The {@link Audit} is embedded.
 * 
 * <p><strong>
 * If use {@code Customer_Id} column for referenced column by @ManyToOne 
 * relationship instead of default primary key, than {@code Customer} entity 
 * must implements the {@code Serializable} interface.</strong>
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 * @see AbstractCustomerBuilder
 * @see Builder
 * @see Address
 * @see Audit
 * @see AuditListener
 * @see LocalDateTime
 * @see ArrayList
 * @see List
 * @see CascadeType
 * @see Column
 * @see Embedded
 * @see EntityListeners
 * @see FetchType
 * @see GeneratedValue
 * @see GenerationType
 * @see Id
 * @see JoinColumn
 * @see OneToMany
 * @see OneToOne
 * @see SequenceGenerator
 * @see Version
 * @see DynamicInsert
 */
@Entity(name = "Customer")
@EntityListeners({AuditListener.class})
@DynamicInsert
@NamedQueries({
    @NamedQuery(name = "getByCustomerId", 
                query = "from Customer c where c.customerId = :customerId"),
    @NamedQuery(name = "getByCustomerName", 
                query = "from Customer c where c.name = :name")
})
@SuppressWarnings({"PersistenceUnitPresent"})
public class Customer implements Auditable, Serializable {

    @Id
    @GeneratedValue(generator = "customerGEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(initialValue = 100, name = "customerGEN", sequenceName = "customerSEQ")
    @Column(name = "Id", nullable = false, unique = true, updatable = false)
    private Long id;
    
    @Column(name = "Customer_Id", nullable = false, unique = true, updatable = false)
    private int customerId;
    
    @Column(name = "Name", nullable = false)
    private String name;
    
    @Column(name = "Contact")
    private String contact;
    
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, 
               mappedBy = "customer", orphanRemoval = true, targetEntity = Job.class)
    private List<Job> jobs;
    
    @Embedded
    private Address address;
    
    @Embedded
    private final Audit audit = new Audit();
    
    @Version
    @Column(name = "Version", nullable = false)
    private int version;

    /**
     * Returns customer's generated ID.
     * @return customer's ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns customer's natural ID.
     * @return customer's natural ID
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Returns customer's name.
     * @return customer's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the contact person by the customer's.
     * @return contact person
     */
    public String getContact() {
        return contact;
    }

    /**
     * Returns customer's ordered jobs as a list.
     * @return customer's jobs
     */
    public List<Job> getJobs() {
        return jobs;
    }

    /**
     * Returns customer's address.
     * @return customer's address
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
     * Returns the {@link Customer}'s entity actual version.
     * @return version No.
     */
    public int getVersion() {
        return version;
    }
    
    /**
     * {@code CustomerBuilder} is used to build instances of 
     * {@code Customer} from values configured by the setters.
     * 
     * <p>The class is achieves the Build design pattern.
     *
     * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
     * @since 0.0.1-SNAPSHOT
     * @see Builder
     * @see AbstractCustomerBuilder
     */
    public static class CustomerBuilder extends 
            AbstractCustomerBuilder<Customer, CustomerBuilder> {

        /**
         * Constructor without parameter.
         */
        public CustomerBuilder() {
            super.entity = new Customer();
        }
        
        /**
         * Constructor with parameter.
         * @param customer {@code Customer}
         */
        public CustomerBuilder(Customer customer) {
            super.entity = customer;
        }
        
        /**
         * Create a new {@code CustomerBuilder} instance.
         * @return a new {@code CustomerBuilder} instance
         */
        public static CustomerBuilder create() {
            return new CustomerBuilder();
        }

        /**
         * Set the {@code Customer}'s natural ID.
         * @param customerId customer's ID
         * @return this
         */
        @Override
        public CustomerBuilder setCustomerId(int customerId) {
            super.entity.customerId = customerId;
            return this;
        }
        
        /**
         * Set the customer's name.
         * @param name name
         * @return this
         */
        @Override
        public CustomerBuilder setName(String name) {
            super.entity.name = name;
            return this;
        }

        /**
         * Set the customer's contact person.
         * @param contact contact person
         * @return this
         */
        @Override
        public CustomerBuilder setContact(String contact) {
            super.entity.contact = contact;
            return this;
        }

        /**
         * Set the {@link Customer}'s job list.
         * @param jobs {@link Customer}'s jobs
         * @return this
         */
        @Override
        public CustomerBuilder setJobs(List<Job> jobs) {
            super.entity.jobs = jobs;
            return this;
        }

        /**
         * Set the customer's {@code Address}.
         * @param address customer's {@code Address}
         * @return this
         */
        @Override
        public CustomerBuilder setAddress(Address address) {
            super.entity.address = address;
            return this;
        }

        /**
         * Return a new {@code Customer} set instance.
         * @return {@code Customer}
         */
        @Override
        public Customer build() {
            return super.entity;
        }
        
    }
    
}
