/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock.model;

import hu.gaborkolozsy.timeclock.model.abstracts.AbstractPayBuilder;
import hu.gaborkolozsy.timeclock.model.abstracts.Auditable;
import hu.gaborkolozsy.timeclock.model.abstracts.Builder;
import hu.gaborkolozsy.timeclock.model.embedded.Audit;
import hu.gaborkolozsy.timeclock.model.embedded.AuditListener;
import java.time.LocalDateTime;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;
import org.hibernate.annotations.DynamicInsert;

/**
 * Represent a pay. This will be stored in database as a table 
 * and it will be called "PAY".
 * 
 * <p>The {@link Audit} is embedded.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 * @see AbstractPayBuilder
 * @see Builder
 * @see Audit
 * @see AuditListener
 * @see LocalDateTime
 * @see CascadeType
 * @see Column
 * @see Embedded
 * @see EntityListeners
 * @see FetchType
 * @see GeneratedValue
 * @see Id
 * @see JoinColumn
 * @see OneToOne
 * @see Version
 * @see DynamicInsert
 */
@Entity(name = "Pay")
@EntityListeners(AuditListener.class)
@DynamicInsert
@SuppressWarnings({"PersistenceUnitPresent", "SerializableClass"})
public class Pay implements Auditable {

    @Id
    @GeneratedValue(generator = "payGEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(initialValue = 1, name = "payGEN", sequenceName = "paySEQ")
    @Column(name = "Id", nullable = false, unique = true, updatable = false)
    private Long id;
    
    @Column(name = "Pay")
    private int pay;
    
    @Column(name = "Currency")
    private String currency;
    
    @Column(name = "Pay_Time")
    private LocalDateTime payTime;
    
    @Column(name = "Payable", nullable = false)
    private boolean payable;
    
    @Column(name = "Paid", nullable = false)
    private boolean paid;
    
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, targetEntity = Job.class)
    @JoinColumn(name = "Order_Number", nullable = false, referencedColumnName = "Order_Number")
    private Job job;
    
    @Embedded
    private final Audit audit = new Audit();
    
    @Version
    @Column(name = "Version", nullable = false)
    private int version;

    /**
     * Returns pay's generated ID.
     * @return pay's ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns {@code Pay}'s pay.
     * @return pay
     */
    public int getPay() {
        return pay;
    }

    /**
     * Returns pay's currency.
     * @return pay's currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Returns pay's pay time.
     * @return pay's pay time
     */
    public LocalDateTime getPayTime() {
        return payTime;
    }

    /**
     * Returns true if the pay is payable. False otherwise.
     * @return true if pay is payable
     */
    public boolean isPayable() {
        return payable;
    }

    /**
     * Returns true if the pay is paid. False otherwise.
     * @return true if pay is paid
     */
    public boolean isPaid() {
        return paid;
    }

    /**
     * Returns the pay's relevant {@link Job}.
     * @return job
     */
    public Job getJob() {
        return job;
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
     * Returns the {@link Pay}'s entity actual version.
     * @return version
     */
    public int getVersion() {
        return version;
    }
    
    /**
     * {@code PayBuilder} is used to build instances of 
     * {@link Pay} from values configured by the setters.
     * 
     * <p>The class is achieves the Build design pattern.
     *
     * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
     * @since 0.0.1-SNAPSHOT
     * @see LocalDateTime
     * @see Builder
     * @see AbstractPayBuilder
     */
    public static class PayBuilder extends AbstractPayBuilder<Pay, PayBuilder> {

        /**
         * Constructor without parameter.
         */
        public PayBuilder() {
            super.entity = new Pay();
        }

        /**
         * Constructor with one parameter.
         * @param pay {@code Pay}
         */
        public PayBuilder(Pay pay) {
            super.entity = pay;
        }

        /**
         * Create a new {@code PayBuilder} instance.
         * @return a new {@code PayBuilder} instance
         */
        public static PayBuilder create() {
            return new PayBuilder();
        }

        /**
         * Set the pay.
         * @param pay pay
         * @return this
         */
        @Override
        public PayBuilder setPay(int pay) {
            super.entity.pay = pay;
            return this;
        }

        /**
         * Set the pay's currency.
         * @param currency currency
         * @return this
         */
        @Override
        public PayBuilder setCurrency(String currency) {
            super.entity.currency = currency;
            return this;
        }

        /**
         * Set the pay's time.
         * @param payTime pay time
         * @return this
         */
        @Override
        public PayBuilder setPayTime(LocalDateTime payTime) {
            super.entity.payTime = payTime;
            return this;
        }

        /**
         * Set the payable.
         * @param payable payable
         * @return this
         */
        @Override
        public PayBuilder isPayable(boolean payable) {
            super.entity.payable = payable;
            return this;
        }

        /**
         * Set the paid.
         * @param paid paid
         * @return this
         */
        @Override
        public PayBuilder isPaid(boolean paid) {
            super.entity.paid = paid;
            return this;
        }

        /**
         * Set the {@code Pay}'s relevant {@code Job}.
         * @param job job
         * @return this
         */
        @Override
        public PayBuilder setJob(Job job) {
            super.entity.job =job;
            return this;
        }

        /**
         * Return a new {@code Pay} set instance.
         * @return {@code Pay}
         */
        @Override
        public Pay build() {
            return entity;
        }

    }
    
}
