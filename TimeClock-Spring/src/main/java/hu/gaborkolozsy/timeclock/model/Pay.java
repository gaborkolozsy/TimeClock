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
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Entity(name = "Pay")
@EntityListeners(AuditListener.class)
@DynamicInsert                                                                  // I have to control this
@SuppressWarnings({"PersistenceUnitPresent", "SerializableClass"})
public class Pay implements Auditable {

    @Id
    @GeneratedValue
    @Column(name = "Pay_Id", nullable = false, unique = true, updatable = false)
    private int payId;
    
    @Column(name = "Order_Number")
    @JoinColumn(referencedColumnName = "Order_Number", nullable = false)
    protected int orderNumber;
    
    @Column(name = "Pay")
    protected int pay;
    
    @Column(name = "Currency")
    protected String currency;
    
    @Column(name = "Pay_Time")
    protected LocalDateTime payTime;
    
    @Column(name = "Payable", nullable = false)
    protected boolean payable;
    
    @Column(name = "Paid", nullable = false)
    protected boolean paid;
    
    @Embedded
    private final Audit audit = new Audit();
    
    @Version
    @Column(name = "Version", nullable = false)
    private int version;

    /**
     * Returns the specified pay's ID.
     * @return pay's ID
     */
    public int getPayId() {
        return payId;
    }

    /**
     * Returns the specified pay's relevant order number.
     * @return pay's order number
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * Returns the specified {@code Pay}'s pay.
     * @return pay
     */
    public int getPay() {
        return pay;
    }

    /**
     * Returns the specified pay's currency.
     * @return pay's currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Returns the specified pay's pay time.
     * @return pay's pay time
     */
    public LocalDateTime getPayTime() {
        return payTime;
    }

    /**
     * Returns true if the specified pay is payable. False otherwise.
     * @return pay is payable
     */
    public boolean isPayable() {
        return payable;
    }

    /**
     * Returns true if the specified pay is paid. False otherwise.
     * @return pay is paid
     */
    public boolean isPaid() {
        return paid;
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
         * Set the No. of order.
         * @param orderNumber number of order
         * @return this
         */
        @Override
        public PayBuilder setOrderNumber(int orderNumber) {
            super.entity.orderNumber = orderNumber;
            return this;
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
         * Return a new {@code Pay} set instance.
         * @return {@code Pay}
         */
        @Override
        public Pay build() {
            return entity;
        }

    }
    
}
