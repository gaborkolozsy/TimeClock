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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;
import org.hibernate.annotations.DynamicInsert;

/**
 * Represent a {@code Pay}. This will be stored in database as a table 
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
 * @see GenerationType
 * @see Id
 * @see JoinColumn
 * @see NamedQueries
 * @see NamedQuery
 * @see OneToOne
 * @see Version
 * @see DynamicInsert
 */
@Entity(name = "Pay")
@EntityListeners(AuditListener.class)
@DynamicInsert
@NamedQueries({
    @NamedQuery(name = "getByPayId", query = "from Pay p where p.payId = :payId"),
    @NamedQuery(name = "getByPayable", query = "from Pay p where p.payable = :payable"),
    @NamedQuery(name = "getByPaid", query = "from Pay p where p.paid = :paid")
})
@SuppressWarnings({"PersistenceUnitPresent", "SerializableClass"})
public class Pay implements Auditable {

    @Id
    @GeneratedValue(generator = "payGEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(initialValue = 1, name = "payGEN", sequenceName = "paySEQ")
    @Column(name = "Id", nullable = false, unique = true, updatable = false)
    private Long id;
    
    @Column(name = "Pay_Id", nullable = false, unique = true, updatable = false)
    private String payId;
    
    @Column(name = "Payment")
    private double payment;
    
    @Column(name = "Currency")
    private String currency;
    
    @Column(name = "Payment_Time")
    private LocalDateTime paymentTime;
    
    @Column(name = "Payable", nullable = false)
    private boolean payable;
    
    @Column(name = "Paid", nullable = false)
    private boolean paid;
    
    @OneToOne
    @JoinColumn(name = "Order_Number", nullable = false, referencedColumnName = "Order_Number")
    private Job job;
    
    @Embedded
    private final Audit audit = new Audit();
    
    @Version
    @Column(name = "Version", nullable = false)
    private int version;

    /**
     * Returns payment's generated ID.
     * @return payment's ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns the payment's ID.<br>
 The payment's ID created for example:
 <blockquote>
     * <table border=2 style=background-color:rgb(220,220,220)>
     *  <tr>
     *   <th>Customer's ID.</th>
     *   <th>Job's Order No.</th>
     *   <th>Pay's No.</th>
     *   <th>Pay's ID.</th>
     *  </tr>
     *  <tr>
     *   <td><center><font color=blue>0150</font></center></td>
     *   <td><center><font color=blue>1382</font></center></td>
     *   <td><center><font color=blue>0001</font></center></td>
     *   <td><center><font color=blue>0150-1382-0001</font></center></td>
     *  </tr>
     * </table>
     * </blockquote>
     * 
     * @return payment's ID
     */
    public String getPayId() {
        return payId;
    }

    /**
     * Returns {@code Pay}'s payment.
     * @return payment
     */
    public double getPayment() {
        return payment;
    }

    /**
     * Returns {@code Pay}'s currency.
     * @return pay's currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Returns {@code Pay}'s payment time.
     * @return pay's payment time
     */
    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    /**
     * Returns true if the payment is payable. False otherwise.
     * @return true if payment is payable
     */
    public boolean isPayable() {
        return payable;
    }

    /**
     * Returns true if the payment is paid. False otherwise.
     * @return true if payment is paid
     */
    public boolean isPaid() {
        return paid;
    }

    /**
     * Returns the {@code Pay}'s relevant {@link Job}.
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
     * {@code Pay} from values configured by the setters.
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
         * Constructor with parameter.
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
         * Set the {@code Pay}'s ID.
         * @param payId pay's ID
         * @return this
         */
        @Override
        public PayBuilder setPayId(String payId) {
            super.entity.payId = payId;
            return this;
        }

        /**
         * Set the payment.
         * @param payment payment
         * @return this
         */
        @Override
        public PayBuilder setPayment(double payment) {
            super.entity.payment = payment;
            return this;
        }

        /**
         * Set the {@code Pay}'s currency.
         * @param currency currency
         * @return this
         */
        @Override
        public PayBuilder setCurrency(String currency) {
            super.entity.currency = currency;
            return this;
        }

        /**
         * Set the {@code Pay}'s time.
         * @param paymentTime payment time
         * @return this
         */
        @Override
        public PayBuilder setPaymentTime(LocalDateTime paymentTime) {
            super.entity.paymentTime = paymentTime;
            return this;
        }

        /**
         * Set the {@code Pay} is payable.
         * @param payable payable
         * @return this
         */
        @Override
        public PayBuilder setPayable(boolean payable) {
            super.entity.payable = payable;
            return this;
        }

        /**
         * Set the {@code Pay} is paid.
         * @param paid paid
         * @return this
         */
        @Override
        public PayBuilder setPaid(boolean paid) {
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
