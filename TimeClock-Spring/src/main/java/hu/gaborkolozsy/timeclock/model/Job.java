/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.model;

import hu.gaborkolozsy.timeclock.model.abstracts.AbstractJobBuilder;
import hu.gaborkolozsy.timeclock.model.abstracts.Auditable;
import hu.gaborkolozsy.timeclock.model.abstracts.Builder;
import hu.gaborkolozsy.timeclock.model.embedded.Audit;
import hu.gaborkolozsy.timeclock.model.embedded.AuditListener;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;
import org.hibernate.annotations.DynamicInsert;

/**
 * Represent a work. This will be stored in database as a table 
 * and it will be called "JOB".
 * 
 * <p>The {@link Audit} is embedded.
 * 
 * <p><strong>
 * If want {@code Customer_Id} or {@code Developer_Id} column for referenced 
 * column by @ManyToOne relationship instead of default primary key, 
 * than {@code Customer} or {@code Developer} entity 
 * must implements the {@code Serializable} interface.</strong>
 *
 * 
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 * @see AbstractJobBuilder
 * @see Builder
 * @see Audit
 * @see AuditListener
 * @see CascadeType
 * @see Column
 * @see Embedded
 * @see EntityListeners
 * @see FetchType
 * @see GeneratedValue
 * @see GenerationType
 * @see Id
 * @see JoinColumn
 * @see ManyToOne
 * @see OneToOne
 * @see SequenceGenerator
 * @see Version
 * @see DynamicInsert
 */
@Entity(name = "Job")
@EntityListeners(AuditListener.class)
@DynamicInsert
@SuppressWarnings({"PersistenceUnitPresent", "SerializableClass"})
public class Job implements Auditable {

    @Id
    @GeneratedValue(generator = "jobGEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(initialValue = 1, name = "jobGEN", sequenceName = "jobSEQ")
    @Column(name = "Id", nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(name = "Order_Number", nullable = false, unique = true, updatable = false)
    private int orderNumber;
    
    @Column(name = "Project", nullable = false)
    private String projectName;
    
    @Column(name = "Branch")
    private String branchName;
    
    @Column(name = "Package")
    private String packageName;
    
    @Column(name = "Class_Name")
    private String className;
    
    @Column(name = "Status", nullable = false)
    private String status;
    
    @Column(name = "Comment")
    private String comment;
    
    @ManyToOne
    @JoinColumn(name = "Customer_Id", nullable = false, referencedColumnName = "Customer_Id")
    private Customer customer;
    
    @ManyToOne
    @JoinColumn(name = "Developer_Id", nullable = false, referencedColumnName = "Developer_Id")
    private Developer developer;
    
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, mappedBy = "job", targetEntity = Pay.class)
    private Pay pay;
    
    @Embedded
    private final Audit audit = new Audit();
    
    @Version
    @Column(name = "Version", nullable = false)
    private int version;

    /**
     * Returns job's generated ID.
     * @return job ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns job's order number.
     * @return job's order number
     */
    public int getOrderNumber() {
        return orderNumber;
    }
    
    /**
     * Returns job's project name.
     * @return job's project name
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Returns the branch name. (e.g. GitHub)
     * @return branch name
     */
    public String getBranchName() {
        return branchName;
    }
    
    /**
     * Returns job's package name.
     * @return job's package name
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * Returns job's class name.
     * @return job's class name
     */
    public String getClassName() {
        return className;
    }

    /**
     * Returns job's status.
     * @return job's status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Returns job's comment.
     * @return job's comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Returns job's relevant {@code Cusomer}.
     * @return job's {@code Customer}
     */
    public Customer getCustomer() {
        return customer;
    }
    
    /**
     * Returns job's developer.
     * @return job's developer
     */
    public Developer getDeveloper() {
        return developer;
    }
    
    /**
     * Returns job's relevant {@code Pay}.
     * @return job's {@code Pay}
     */
    public Pay getPay() {
        return pay;
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
     * Returns the {@link Job}'s entity actual version.
     * @return version No.
     */
    public int getVersion() {
        return version;
    }
    
    /**
     * {@code JobBuilder} is used to build instances of 
     * {@link Job} from values configured by the setters.
     * 
     * <p>The class is achieves the Build design pattern.
     *
     * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
     * @since 0.0.1-SNAPSHOT
     * @see Builder
     * @see AbstractJobBuilder
     */
    public static class JobBuilder extends AbstractJobBuilder<Job, JobBuilder> {

        /**
         * Constructor without parameter.
         */
        public JobBuilder() {
            super.entity = new Job();
        }

        /**
         * Constructor with one parameter.
         * @param job {@code Job}
         */
        public JobBuilder(Job job) {
            super.entity = job;
        }

        /**
         * Create a new {@code JobBuilder} instance.
         * @return a new {@code JobBuilder} instance
         */
        public static JobBuilder create() {
            return new JobBuilder();
        }
        
        /**
         * Set the job's order number for {@code Job} entity.
         * @param orderNumber job's order number
         * @return this
         */
        @Override
        public JobBuilder setOrderNumber(int orderNumber) {
            super.entity.orderNumber = orderNumber;
            return this;
        }

        /**
         * Set the project's name for {@code Job} entity.
         * @param projectName project's name
         * @return this
         */
        @Override
        public JobBuilder setProjectName(String projectName) {
            super.entity.projectName = projectName;
            return this;
        }
        
        /**
         * Set the branch's name for {@code Job} entity. (e.g. GitHub)
         * @param branchName branch's name
         * @return this
         */
        @Override
        public JobBuilder setBranchName(String branchName) {
            super.entity.branchName = branchName;
            return this;
        }      

        /**
         * Set the package's name for {@code Job} entity.
         * @param packageName package's name
         * @return this
         */
        @Override
        public JobBuilder setPackageName(String packageName) {
            super.entity.packageName = packageName;
            return this;
        }

        /**
         * Set the class' name for {@code Job} entity.
         * @param className class' name
         * @return this
         */
        @Override
        public JobBuilder setClassName(String className) {
            super.entity.className = className;
            return this;
        }

        /**
         * Set the status for {@code Job} entity.
         * @param status status
         * @return this
         */
        @Override
        public JobBuilder setStatus(String status) {
            super.entity.status = status;
            return this;
        }

        /**
         * Set the comment for {@code Job} entity.
         * @param comment comment
         * @return this
         */
        @Override
        public JobBuilder setComment(String comment) {
            super.entity.comment = comment;
            return this;
        }

        /**
         * Set the {@code Job} entity's relevant customer.
         * @param customer customer
         * @return this
         */
        @Override
        public JobBuilder setCustomer(Customer customer) {
            super.entity.customer = customer;
            return this;
        }

        /**
         * Set the developer for {@code Job} entity.
         * @param developer developer
         * @return this
         */
        @Override
        public JobBuilder setDeveloper(Developer developer) {
            super.entity.developer = developer;
            return this;
        }

        /**
         * Set the pay for {@code Job} entity.
         * @param pay pay
         * @return this
         */
        @Override
        public JobBuilder setPay(Pay pay) {
            super.entity.pay = pay;
            return this;
        }
        
        /**
         * Return a new {@code Job} set instance.
         * @return {@code Job}
         */
        @Override
        public Job build() {
            return super.entity;
        }

    }
    
}
