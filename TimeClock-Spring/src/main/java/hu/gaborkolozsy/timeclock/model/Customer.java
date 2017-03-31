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
import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import org.hibernate.annotations.DynamicInsert;

/**
 * Represent a customer. This will be stored in database and it will be 
 * called "CUSTOMER".
 * 
 * <p>The {@link Audit} is embedded.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 * @see AbstractCustomerBuilder
 * @see Builder
 * @see Address
 * @see AuditListener
 * @see LocalDateTime
 * @see Column
 * @see Embedded
 * @see Entity
 * @see EntityListeners
 * @see GeneratedValue
 * @see Id
 * @see JoinColumn
 * @see OneToOne
 * @see Version
 * @see DynamicInsert
 */
@Entity(name = "Customer")
@EntityListeners({AuditListener.class})
@DynamicInsert                                                                  // I have to control this
@NamedQuery(name = "all", query = "Select c from Customer c")                   // ??? talán @NamedQueries egy egész külön fájl vagy osztály
@SuppressWarnings({"PersistenceUnitPresent", "SerializableClass"})
public class Customer implements Auditable {

    @Id
    @GeneratedValue
    @Column(name = "Customer_Id", nullable = false, unique = true, updatable = false)
    private int customerId;
    
    @Column(name = "Order_Number")
    @JoinColumn(referencedColumnName = "Order_Number", nullable = false)
    private int orderNumber;
    
    @Column(name = "Name", nullable = false)
    private String name;
    
    @Column(name = "Contact", nullable = false)
    private String contact;
    
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, /*mappedBy = "Customer",*/ targetEntity = Job.class)
    private Set<Job> job;
    
    @Embedded
    private final Address address = new Address();
    
    @Embedded
    private final Audit audit = new Audit();
    
    @Version
    @Column(name = "Version", nullable = false)
    private int version;

    /**
     * Returns the specified customer ID.
     * @return customer ID
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Returns the specified customer's relevant order number.
     * @return order number
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * Returns the specified customer's name.
     * @return customer's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the contact person by the specified customer's.
     * @return contact person
     */
    public String getContact() {
        return contact;
    }

    /**
     * Returns the specified customer's jobs as a set.
     * @return customer's jobs
     */
    public Set<Job> getJob() {
        return job;
    }

    /**
     * Returns the specified customer's address.
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
     * @return version
     */
    public int getVersion() {
        return version;
    }
    
    /**
    * {@code CustomerBuilder} is used to build instances of 
    * {@link Customer} from values configured by the setters.
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
         * Constructor with one parameter.
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
         * Set the customer's relevant order number.
         * @param orderNumber order number
         * @return this
         */
        @Override
        public CustomerBuilder setOrderNumber(int orderNumber) {
            super.entity.orderNumber = orderNumber;
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
         * Return a new {@code Customer} set instance.
         * @return {@code Customer}
         */
        @Override
        public Customer build() {
            return entity;
        }
        
    }
    
}
